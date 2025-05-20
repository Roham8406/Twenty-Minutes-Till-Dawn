package com.tilldawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
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
    private final SelectBox<String> securityQuestion;
    private final TextField securityAnswer;
    public Table table;
    private Table fields;
    private final RegisterMenuController controller;

    public RegisterMenuView(RegisterMenuController controller, Skin skin) {
        this.controller = controller;
        this.register = new TextButton("Sign Up", skin);
        this.usernameField = new TextField("", skin);
        this.passwordLabel = new Label("Password: ", skin);
        this.table = new Table();
        this.fields = new Table();
        this.usernameLabel = new Label("Username: ", skin);
        this.passwordField = new TextField("", skin);
        this.securityAnswer = new TextField("", skin);
        this.securityQuestion = new SelectBox<>(skin);
        for (SecurityQuestions value : SecurityQuestions.values()) {
            this.securityQuestion.setItems(value.getQuestion());
        }
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
        table.add(securityQuestion).width(500).colspan(2).center();
        table.row().pad(10, 0 , 10 , 0);
        table.add(securityAnswer).width(500).colspan(2).center();
        table.row().pad(10, 0 , 10 , 0);
        table.add(register).width(500).colspan(2).center();

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

    public void alert(String theFieldsAreEssential) {
        //todo
    }
}
