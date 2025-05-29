package com.tilldawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.Control.LoginMenuController;
import com.tilldawn.Main;

public class LoginMenuView implements Screen {
    private Stage stage;
    private final TextButton register;
    private final Label usernameLabel;
    private final TextField usernameField;
    private final Label passwordLabel;
    private final TextField passwordField;
    private final TextButton forgotPassword;
    private final TextButton playAsGuest;
    private final TextButton loginButton;
    public Table table;
    private Table fields;
    private final LoginMenuController controller;
    private Skin skin;

    public LoginMenuView(LoginMenuController controller, Skin skin) {
        this.skin = skin;
        this.controller = controller;
        this.register = new TextButton(Main.getLanguage().SignUp, skin);
        this.usernameField = new TextField("", skin);
        this.passwordLabel = new Label(Main.getLanguage().Password + ": ", skin);
        this.forgotPassword = new TextButton(Main.getLanguage().ForgotPassword, skin);
        this.table = new Table();
        this.fields = new Table();
        this.playAsGuest = new TextButton(Main.getLanguage().ContinueAsGuest, skin);
        this.loginButton = new TextButton(Main.getLanguage().Login, skin);
        this.usernameLabel = new Label(Main.getLanguage().Username + ": ", skin);
        this.passwordField = new TextField("", skin);

        controller.setView(this);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table.setFillParent(true);
        table.center();
        fields.center();
        fields.add(usernameLabel).width(100).padRight(100);
        fields.add(usernameField).width(300);
        fields.row().pad(10, 0, 10, 0);
        fields.add(passwordLabel).width(100).padRight(100);
        fields.add(passwordField).width(300);
        fields.row().pad(10, 0, 10, 0);
        table.add(fields).width(500).colspan(2);
        table.row().pad(10, 0, 10, 0);
        table.add(loginButton).width(500).colspan(2).center();
        table.row().pad(10, 0, 10, 0);
        table.add(register).width(500).padRight(30);
        table.add(forgotPassword).width(500);
        table.row().pad(10, 0, 10, 0);
        table.add(playAsGuest).width(500).colspan(2).center();

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

    public TextButton getRegister() {
        return register;
    }

    public TextField getPasswordField() {
        return passwordField;
    }

    public TextButton getForgotPassword() {
        return forgotPassword;
    }

    public TextField getUsernameField() {
        return usernameField;
    }

    public TextButton getLoginButton() {
        return loginButton;
    }

    public TextButton getPlayAsGuest() {
        return playAsGuest;
    }

    public void alert(String message, Integer timer) {
        final Table alertBox = new Table(skin);
        alertBox.setSize(1000, 50);
        alertBox.setPosition(Gdx.graphics.getWidth() / 2f,0,Align.bottom);

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

    public Stage getStage() {
        return stage;
    }
}
