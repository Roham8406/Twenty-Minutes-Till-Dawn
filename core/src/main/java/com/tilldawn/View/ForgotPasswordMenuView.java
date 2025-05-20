package com.tilldawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.Control.ForgotPasswordMenuController;
import com.tilldawn.Main;

public class ForgotPasswordMenuView implements Screen {
    private Stage stage;
    private final TextButton findUsername;
    private final Label usernameLabel;
    private final TextField usernameField;
    private final Label securityQuestion;
    private final TextField securityAnswerField;
    private final TextButton authorize;
    private final Label newPassword;
    private final TextField newPasswordField;
    private final TextButton changePassword;
    private int state = 0;
    public Table table;
    private final ForgotPasswordMenuController controller;

    public ForgotPasswordMenuView(ForgotPasswordMenuController controller, Skin skin) {
        this.controller = controller;
        this.findUsername = new TextButton("Find User", skin);
        this.usernameField = new TextField("", skin);
        this.usernameLabel = new Label("Username: ", skin);
        this.authorize = new TextButton("Authorize answer", skin);
        this.table = new Table();
        this.securityQuestion = new Label("security_question_being_loaded", skin);
        this.securityAnswerField = new TextField("", skin);
        this.newPassword = new Label("New Password: ", skin);
        this.newPasswordField = new TextField("", skin);
        this.changePassword = new TextButton("Change password", skin);

        controller.setView(this);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table.setFillParent(true);
        table.center();
        switch (state) {
            case 0: {
                table.add(usernameLabel).width(100).padRight(40);
                table.add(usernameField).width(300);
                table.row().pad(10,0,10,0);
                table.add(findUsername).colspan(2).width(500);
            } break;
            case 1: {
                table.add(securityQuestion).width(300);
                table.row().pad(10,0,10,0);
                table.add(securityAnswerField).width(300);
                table.row().pad(10,0,10,0);
                table.add(authorize).width(300);
            } break;
            case 2: {
                table.add(newPassword).width(100).padRight(40);
                table.add(newPasswordField).width(300);
                table.row().pad(10,0,10,0);
                table.add(changePassword).colspan(2).width(500);
            } break;

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


    public void alert(String theFieldsAreEssential) {
        //todo
    }
}
