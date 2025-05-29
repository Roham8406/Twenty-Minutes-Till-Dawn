package com.tilldawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.Control.MainMenuController;
import com.tilldawn.Control.ProfileMenuController;
import com.tilldawn.Main;
import com.tilldawn.Model.Avatar;
import com.tilldawn.Model.User;

public class ProfileMenuView implements Screen {
    private Stage stage;
    private final ImageButton avatarPreview;
    private final Label usernameLabel;
    private final TextField username;
    private final Label passwordLabel;
    private final TextField password;
    private final TextButton changeAvatar;
    private final TextButton deleteAccount;
    private final TextButton changeInfo;
    private final Table chooseAvatar;
    private final TextButton chooseLocalAvatar;
    private final TextButton apply;
    public Table table;
    private final ProfileMenuController controller;
    private boolean changingAvatar;
    private final Skin skin;

    public ProfileMenuView(ProfileMenuController controller, Skin skin) {
        this.controller = controller;
        this.table = new Table();
        this.usernameLabel = new Label(Main.getLanguage().Username, skin);
        this.username = new TextField(Main.getMain().getCurrentUser().getUsername(), skin);
        this.passwordLabel = new Label(Main.getLanguage().Password, skin);
        this.password = new TextField("", skin);
        this.changeAvatar = new TextButton(Main.getLanguage().ChangeAvatar, skin);
        this.deleteAccount = new TextButton(Main.getLanguage().DeleteAccount, skin);
        this.changeInfo = new TextButton(Main.getLanguage().ChangeInfo, skin);
        this.changingAvatar = false;
        this.skin = skin;
        this.apply = new TextButton(Main.getLanguage().Apply, skin);
        this.chooseAvatar = null;
        this.chooseLocalAvatar = null;
        this.avatarPreview = null;
        controller.setView(this);
    }

    public ProfileMenuView(ProfileMenuController controller, Skin skin, boolean changingAvatar) {
        this.controller = controller;
        this.skin = skin;
        this.changingAvatar = changingAvatar;
        this.chooseAvatar = new Table();
        Main.getMain().getCurrentUser().getAvatar().getAvatars(this.chooseAvatar);
        this.chooseLocalAvatar = new TextButton(Main.getLanguage().ChooseLocalAvatar, skin);
        this.avatarPreview = new Avatar(controller.getPath()).getActor(skin);
        this.usernameLabel = null;
        this.username = null;
        this.passwordLabel = null;
        this.password = null;
        this.changeAvatar = null;
        this.deleteAccount = null;
        this.changeInfo = null;
        this.table = new Table(skin);
        this.apply = new TextButton(Main.getLanguage().Apply, skin);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table.setFillParent(true);
        table.center();

        if (changingAvatar) {
            table.add(new Avatar(controller.getPath()).getActor(skin)).height(200);
            table.row().pad(10, 0 , 10, 0);
            table.add(chooseAvatar).height(100);
            table.row().pad(10, 0 , 10, 0);
            table.add(chooseLocalAvatar).width(700);
            table.row().pad(10, 0 , 10, 0);
            table.add(apply).width(700);
        } else {
            table.add(usernameLabel).width(80).padRight(50);
            table.add(username).width(300);
            table.row().pad(10, 0 , 10, 0);
            table.add(passwordLabel).width(80).padRight(50);
            table.add(password).width(300);
            table.row().pad(10, 0 , 10, 0);
            table.add(changeInfo).colspan(2).width(500);
            table.row().pad(10, 0 , 10, 0);
            table.add(changeAvatar).colspan(2).width(500);
            table.row().pad(10, 0 , 10, 0);
            table.add(deleteAccount).colspan(2).width(500);
            table.row().pad(10, 0 , 10, 0);
            table.add(apply).width(500).colspan(2);
        }

        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        Main.getMain().startBatch();
        ScreenUtils.clear(0.2f, 0.5f, 0.4f, 1);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        Main.getMain().endBatch();
        controller.handleMainMenuButtons();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    public TextField getUsername() {
        return username;
    }

    public TextField getPassword() {
        return password;
    }

    public TextButton getDeleteAccount() {
        return deleteAccount;
    }

    public Table getChooseAvatar() {
        return chooseAvatar;
    }

    public TextButton getApply() {
        return apply;
    }

    public TextButton getChangeAvatar() {
        return changeAvatar;
    }

    public TextButton getChangeInfo() {
        return changeInfo;
    }

    public TextButton getChooseLocalAvatar() {
        return chooseLocalAvatar;
    }

    public Stage getStage() {
        return stage;
    }

    public void alert(String message, Integer timer) {
        final Table alertBox = new Table(skin);
        alertBox.setSize(1000, 50);
        alertBox.setPosition(Gdx.graphics.getWidth() / 2f,0, Align.bottom);

        Label label = new Label(message, skin);
        alertBox.add(label);
        alertBox.setBackground("progress-bar-health-knob");
        stage.addActor(alertBox);

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                alertBox.remove();
            }
        }, timer);

    }

    public boolean isChangingAvatar() {
        return changingAvatar;
    }

    public ImageButton getAvatarPreview() {
        return avatarPreview;
    }
}
