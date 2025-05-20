package com.tilldawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.Control.ForgotPasswordMenuController;
import com.tilldawn.Control.MainMenuController;
import com.tilldawn.Main;
import com.tilldawn.Model.GameAssetManager;
import com.tilldawn.Model.User;

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
    private final Integer state;
    public Table table;
    private Skin skin;
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
        this.skin = skin;
        this.state = 0;
        controller.setView(this);
    }

    public ForgotPasswordMenuView(ForgotPasswordMenuController controller, Skin skin, Integer state) {
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
        this.skin = skin;
        this.state = state;
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
                table.add(authorize).width(600);
            } break;
            case 2: {
                table.add(newPassword).width(200).padRight(40);
                table.add(newPasswordField).width(300);
                table.row().pad(10,0,10,0);
                table.add(changePassword).colspan(2).width(600);
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

    public TextField getUsernameField() {
        return usernameField;
    }

    public TextButton getAuthorize() {
        return authorize;
    }

    public TextButton getFindUsername() {
        return findUsername;
    }

    public TextButton getChangePassword() {
        return changePassword;
    }

    public TextField getNewPasswordField() {
        return newPasswordField;
    }

    public TextField getSecurityAnswerField() {
        return securityAnswerField;
    }

    public void setSecurityQuestion(String securityQuestion) {
        this.securityQuestion.setText(securityQuestion);
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

    public ForgotPasswordMenuView setState(int i) {
        this.dispose();
        Main.getMain().setScreen(new ForgotPasswordMenuView(controller, skin, state + 1));
        return (ForgotPasswordMenuView) Main.getMain().getScreen();
    }

    public Integer getState() {
        return state;
    }
}
