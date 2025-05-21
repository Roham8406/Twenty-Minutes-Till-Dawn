package com.tilldawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.Control.MainMenuController;
import com.tilldawn.Control.ProfileMenuController;
import com.tilldawn.Main;
import com.tilldawn.Model.User;

public class ProfileMenuView implements Screen {
    private Stage stage;
    private final Label usernameLabel;
    private final TextField username;
    private final Label passwordLabel;
    private final TextField password;
    private final TextButton changeAvatar;
    private final TextButton deleteAccount;
    private final TextButton changeInfo;
    private final SelectBox<ImageButton> chooseAvatar;
    private final TextButton chooseLocalAvatar;
    private final TextButton apply;
    public Table table;
    private final ProfileMenuController controller;
    private boolean changingAvatar;
    private final Skin skin;

    public ProfileMenuView(ProfileMenuController controller, Skin skin) {
        this.controller = controller;
        this.table = new Table();
        this.usernameLabel = new Label("Username", skin);
        this.username = new TextField(Main.getMain().getCurrentUser().getUsername(), skin);
        this.passwordLabel = new Label("Password", skin);
        this.password = new TextField("", skin);
        this.changeAvatar = new TextButton("Change Avatar", skin);
        this.deleteAccount = new TextButton("Delete Account", skin);
        this.changeInfo = new TextButton("Change Info", skin);
        this.changingAvatar = false;
        this.skin = skin;
        this.apply = new TextButton("Apply", skin);
        this.chooseAvatar = null;
        this.chooseLocalAvatar = null;
        controller.setView(this);
    }

    public ProfileMenuView(ProfileMenuController controller, Skin skin, boolean changingAvatar) {
        this.controller = controller;
        this.skin = skin;
        this.changingAvatar = changingAvatar;
        this.chooseAvatar = new SelectBox<>(skin);
        this.chooseAvatar.setItems(Main.getMain().getCurrentUser().getAvatar().getDefaultAvatars());
        this.chooseAvatar.setSelectedIndex(0);
        this.chooseLocalAvatar = new TextButton("Choose Local Avatar", skin);
        this.usernameLabel = null;
        this.username = null;
        this.passwordLabel = null;
        this.password = null;
        this.changeAvatar = null;
        this.deleteAccount = null;
        this.changeInfo = null;
        this.table = new Table(skin);
        this.apply = new TextButton("Apply", skin);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table.setFillParent(true);
        table.top();

        if (changingAvatar) {
            table.add(chooseAvatar).width(500);
            table.row().pad(10, 0 , 10, 0);
            table.add(chooseLocalAvatar).width(500);
            table.row().pad(10, 0 , 10, 0);
            table.add(apply).width(500);
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
        ScreenUtils.clear(0.2f, 0.5f, 0.4f, 1);
        Main.getBatch().begin();
        Main.getBatch().end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
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

    public SelectBox<ImageButton> getChooseAvatar() {
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
}
