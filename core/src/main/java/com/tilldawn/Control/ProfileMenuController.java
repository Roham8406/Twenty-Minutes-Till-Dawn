package com.tilldawn.Control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.tilldawn.Main;
import com.tilldawn.Model.Avatar;
import com.tilldawn.Model.GameAssetManager;
import com.tilldawn.Model.Response;
import com.tilldawn.View.LoginMenuView;
import com.tilldawn.View.MainMenuView;
import com.tilldawn.View.PreGameMenuView;
import com.tilldawn.View.ProfileMenuView;
import com.tilldawn.service.UserSql;

import javax.swing.*;
import java.awt.dnd.DropTarget;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class ProfileMenuController extends MenuController {
    private ProfileMenuView view;
    private String path;

    public ProfileMenuController() {
        path = Main.getMain().getCurrentUser().getAvatar().getAvatar();
    }

    public void setView(ProfileMenuView view) {
        this.view = view;
    }

    public void handleMainMenuButtons() {
        addClickSoundToButtons(view.getStage().getRoot());
        if (view != null) {
            if (!view.isChangingAvatar()) {
                if (view.getChangeInfo().isChecked()) {
                    String username = view.getUsername().getText();
                    String password = view.getPassword().getText();
                    if (!username.equals(Main.getMain().getCurrentUser().getUsername()) &&
                        !username.isEmpty()) {
                        if (!UserSql.isUsernameValid(username).matches()) {
                            view.alert("The username must contain only a-zA-z0-9_", 5);
                            view.getChangeInfo().setChecked(false);
                            return;
                        }
                        Response response = Main.getMain().getUserSql().isUsernameAvailable(username);
                        if (!response.isSuccess()) {
                            view.alert(response.getMessage(), 5);
                            view.getChangeInfo().setChecked(false);
                            return;
                        }
                    } else {
                        username = Main.getMain().getCurrentUser().getUsername();
                    }
                    if (!password.isEmpty()) {
                        if (!UserSql.isPasswordValid(password).matches()) {
                            view.alert("The password is too easy", 5);
                            view.getChangeInfo().setChecked(false);
                            return;
                        }
                    } else {
                        password = null;
                    }
                    Response resp = Main.getMain().getUserSql().updateInfo(username, password);
                    if (!resp.isSuccess()) {
                        view.alert(resp.getMessage(), 5);
                    }
                    view.getChangeInfo().setChecked(false);
                }
                if (view.getChangeAvatar().isChecked()) {
                    Main.getMain().getScreen().dispose();
                    Main.getMain().setScreen(new ProfileMenuView(this, GameAssetManager.getGameAssetManager().getSkin(), true));
                    view = (ProfileMenuView) Main.getMain().getScreen();
                    return;
                }
                if (view.getDeleteAccount().isChecked()) {
                    Response response = Main.getMain().getUserSql().deleteAccount();
                    if (!response.isSuccess()) {
                        view.alert(response.getMessage(), 5);
                    }
                    Main.getMain().setCurrentUser(null);
                    Main.getMain().getScreen().dispose();
                    Main.getMain().setScreen(new MainMenuView(new MainMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
                    return;
                }
                if (view.getApply().isChecked()) {
                    Main.getMain().getScreen().dispose();
                    Main.getMain().setScreen(new MainMenuView(new MainMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
                }
            } else {
                for (int i = 0; i < view.getChooseAvatar().getChildren().size; i++) {
                    Actor child = view.getChooseAvatar().getChildren().get(i);
                    if (child instanceof ImageButton) {
                        ImageButton imageButton = (ImageButton) child;
                        if (imageButton.isChecked()) {
                            path = Main.getMain().getCurrentUser().getAvatar().getDefaultAvatars().get(i).getAvatar();
                            updateProfile();
                        }
                    }
                }
                if (view.getChooseLocalAvatar().isChecked()) {
                    String path = openFileChooser();
                    if (path == null) {
                        view.alert("No file selected", 5);
                        view.getChooseLocalAvatar().setChecked(false);
                        return;
                    }
                    copyFileToAssets(path);
                    this.path = Gdx.files.local("avatars/" + Main.getMain().getCurrentUser().getUsername() + "_Avatar.png").path();
                    updateProfile();
                }
//                new DropTarget(Main.getCanvas(), new DropTargetListener() { //TODO
//                    @Override
//                    public void drop(DropTargetDropEvent event) {
//                        event.acceptDrop(DnDConstants.ACTION_COPY);
//                        try {
//                            Transferable transferable = event.getTransferable();
//                            List<File> droppedFiles = (List<File>) transferable.getTransferData(DataFlavor.javaFileListFlavor);
//                            for (File file : droppedFiles) {
//                                String filePath = file.getAbsolutePath();
//                                System.out.println("Dropped file: " + filePath);
//                                // You can send this path to your game logic using a listener or static setter
//                            }
//                        } catch (Exception ex) {
//                            ex.printStackTrace();
//                        }
//                    }
//
//                    public void dragEnter(DropTargetDragEvent dtde) {}
//                    public void dragOver(DropTargetDragEvent dtde) {}
//                    public void dropActionChanged(DropTargetDragEvent dtde) {}
//                    public void dragExit(DropTargetEvent dte) {}
//                });

                if (view.getApply().isChecked()) {
                    Response response = Main.getMain().getUserSql().updateAvatar(path);
                    if (!response.isSuccess()) {
                        view.alert(response.getMessage(), 5);
                        view.getApply().setChecked(false);
                        return;
                    }
                    Main.getMain().getCurrentUser().setAvatar(new Avatar(this.path));
                    Main.getMain().getScreen().dispose();
                    Main.getMain().setScreen(new ProfileMenuView(new ProfileMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
                }
            }
        }
    }

    private String openFileChooser() {
        JFileChooser chooser = new JFileChooser();
        int result = chooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
            return selectedFile.getAbsolutePath();
        }
        return null;
    }


    private Response copyFileToAssets(String source) {
        File sourceFile = new File(source);
        if (!sourceFile.exists()) {
            return new Response("Source file does not exist.", false);
        }

        try {
            String username = Main.getMain().getCurrentUser().getUsername();
            FileHandle target = Gdx.files.local("avatars/" + username + "_Avatar.png");

            // Ensure the directory exists
            target.parent().mkdirs();

            // Copy the file
            FileInputStream in = new FileInputStream(sourceFile);
            target.write(in, false);
            in.close();

            return new Response("", true);
        } catch (IOException e) {
            return new Response("Failed to copy file: " + e.getMessage(), false);
        }
    }


    public String getPath() {
        return path;
    }

    public void updateProfile() {
//        Texture buttonTexture = new Texture(Gdx.files.internal(path));
//        TextureRegionDrawable drawable = new TextureRegionDrawable(new TextureRegion(buttonTexture));
//        view.getAvatarPreview().setBackground(drawable);
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new ProfileMenuView(this, GameAssetManager.getGameAssetManager().getSkin(), true));
        view = (ProfileMenuView) Main.getMain().getScreen();
    }
}
