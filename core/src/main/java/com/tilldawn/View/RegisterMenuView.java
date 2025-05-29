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
import com.tilldawn.Control.RegisterMenuController;
import com.tilldawn.Main;
import com.tilldawn.Model.SecurityQuestions;

import java.util.Arrays;

public class RegisterMenuView implements Screen {
    private Stage stage;
    private final TextButton register;
    private final Label usernameLabel;
    private final TextField usernameField;
    private final Label passwordLabel;
    private final TextField passwordField;
    private final TextButton playAsGuest;
    private final SelectBox<String> securityQuestion;
    private final TextField securityAnswer;
    public Table table;
    private final Skin skin;
    private final RegisterMenuController controller;

    public RegisterMenuView(RegisterMenuController controller, Skin skin) {
        this.controller = controller;
        this.skin = skin;
        this.register = new TextButton("Sign Up", skin);
        this.usernameField = new TextField("", skin);
        this.passwordLabel = new Label("Password: ", skin);
        this.table = new Table();
        this.usernameLabel = new Label("Username: ", skin);
        this.passwordField = new TextField("", skin);
        this.securityAnswer = new TextField("", skin);
        this.securityQuestion = new SelectBox<>(skin);
        for (SecurityQuestions value : SecurityQuestions.values()) {
            this.securityQuestion.setItems(value.getQuestion());
        }
        this.playAsGuest = new TextButton("Continue As Guest!", skin);
        this.securityQuestion.setItems(Arrays.stream(SecurityQuestions.values())
                                        .map(SecurityQuestions::getQuestion)
                                        .toArray(String[]::new));

        controller.setView(this);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table.setFillParent(true);
        table.center();
        table.add(usernameLabel).width(100).padRight(100);
        table.add(usernameField).width(300);
        table.row().pad(10, 0 , 10, 0);
        table.add(passwordLabel).width(100).padRight(100);
        table.add(passwordField).width(300);
        table.row().pad(10, 0 , 10 , 0);
        table.add(securityQuestion).width(800).colspan(2).center();
        table.row().pad(10, 0 , 10 , 0);
        table.add(securityAnswer).width(500).colspan(2).center();
        table.row().pad(10, 0 , 10 , 0);
        table.add(register).width(500).colspan(2).center();
        table.row().pad(10, 0 , 10 , 0);
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

    public TextField getUsernameField() {
        return usernameField;
    }

    public SelectBox<String> getSecurityQuestion() {
        return securityQuestion;
    }

    public TextField getSecurityAnswer() {
        return securityAnswer;
    }

    public TextButton getPlayAsGuest() {
        return playAsGuest;
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

    public Stage getStage() {
        return stage;
    }
}
