package com.tilldawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.Control.ControlSettingMenuController;
import com.tilldawn.Control.SettingMenuController;
import com.tilldawn.Main;
import com.tilldawn.Model.MusicTracks;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;

public class ControlSettingsMenuView implements Screen {
    private Stage stage;
    private Skin skin;
    private final Label upLabel;
    private final Label downLabel;
    private final Label leftLabel;
    private final Label rightLabel;
    private final Label autoAimLabel;
    private final Label reloadLabel;
    private final Label shootLabel;
    private final TextButton upTextButton;
    private final TextButton downTextButton;
    private final TextButton leftTextButton;
    private final TextButton rightTextButton;
    private final TextButton autoAimTextButton;
    private final TextButton reloadTextButton;
    private final TextButton shootTextButton;
    private final TextButton mainMenu;
    public Table table;
    private final ControlSettingMenuController controller;

    public ControlSettingsMenuView(ControlSettingMenuController controller, Skin skin) {
        this.controller = controller;
        this.upLabel = new Label(Main.getLanguage().Up, skin);
        this.downLabel = new Label(Main.getLanguage().Down, skin);
        this.leftLabel = new Label(Main.getLanguage().Left, skin);
        this.rightLabel = new Label(Main.getLanguage().Right, skin);
        this.autoAimLabel = new Label(Main.getLanguage().AutoAim, skin);
        this.reloadLabel = new Label(Main.getLanguage().Reload, skin);
        this.shootLabel = new Label(Main.getLanguage().Shoot, skin);
        Function<String, String> keyDecoder = key ->
            (Main.getMain().getControl().getKeys().get(key) != Input.Buttons.LEFT) ?
                Input.Keys.toString(Main.getMain().getControl().getKeys().get(key)) :
                "Click";
        this.upTextButton = new TextButton(keyDecoder.apply("up"), skin);
        this.downTextButton = new TextButton(keyDecoder.apply("down"), skin);
        this.leftTextButton = new TextButton(keyDecoder.apply("left"), skin);
        this.rightTextButton = new TextButton(keyDecoder.apply("right"), skin);
        this.autoAimTextButton = new TextButton(keyDecoder.apply("autoAim"), skin);
        this.reloadTextButton = new TextButton(keyDecoder.apply("reload"), skin);
        this.shootTextButton = new TextButton(keyDecoder.apply("shoot"), skin);
        this.mainMenu = new TextButton(Main.getLanguage().MainMenu, skin);
        this.table = new Table();
        this.skin = skin;

        controller.setView(this);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table.setFillParent(true);
        table.center();

        table.add(autoAimLabel).width(100).padRight(40);
        table.add(autoAimTextButton).width(200).padRight(60);
        table.add(upLabel).width(100).padRight(40);
        table.add(upTextButton).width(200).padRight(60);
        table.add(reloadLabel).width(100).padRight(40);
        table.add(reloadTextButton).width(200).padRight(60);
        table.row().pad(10, 0 , 10, 0);
        table.add(leftLabel).width(100).padRight(40);
        table.add(leftTextButton).width(200).padRight(60);
        table.add(downLabel).width(100).padRight(40);
        table.add(downTextButton).width(200).padRight(60);
        table.add(rightLabel).width(100).padRight(40);
        table.add(rightTextButton).width(200).padRight(60);
        table.row().pad(10, 0 , 10, 0);
        table.add(shootLabel).width(100).padRight(40);
        table.add(shootTextButton).width(200).padRight(60);
        table.row().pad(10, 0 , 10, 0);
        table.add(mainMenu).colspan(6).width(500);

        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        Main.getMain().startBatch();
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

    private String keyDecoder (int key) {
        return key == Input.Buttons.LEFT ? "Click" : Input.Keys.toString(key);
    }

    public TextButton getAutoAimTextButton() {
        return autoAimTextButton;
    }

    public void setAutoAim() {
        Integer keyCode = Main.getMain().getControl().changeAutoAim(controller);
        autoAimTextButton.setText(keyDecoder(keyCode));
    }

    public TextButton getDownTextButton() {
        return downTextButton;
    }

    public void setDown() {
        Integer keyCode = Main.getMain().getControl().changeDown(controller);
        downTextButton.setText(keyDecoder(keyCode));
    }

    public TextButton getLeftTextButton() {
        return leftTextButton;
    }

    public void setLeft() {
        Integer keyCode = Main.getMain().getControl().changeLeft(controller);
        leftTextButton.setText(keyDecoder(keyCode));
    }

    public TextButton getReloadTextButton() {
        return reloadTextButton;
    }

    public void setReload() {
        Integer keyCode = Main.getMain().getControl().changeReload(controller);
        reloadLabel.setText(keyDecoder(keyCode));
    }


    public TextButton getRightTextButton() {
        return rightTextButton;
    }

    public void setRight() {
        Integer keyCode = Main.getMain().getControl().changeRight(controller);
        rightTextButton.setText(keyDecoder(keyCode));
    }

    public TextButton getShootTextButton() {
        return shootTextButton;
    }

    public void setShoot() {
        Integer keyCode = Main.getMain().getControl().changeShoot(controller);
        shootTextButton.setText(keyDecoder(keyCode));
    }


    public TextButton getUpTextButton() {
        return upTextButton;
    }

    public void setUpKey() {
        Integer keyCode = Main.getMain().getControl().changeUp(controller);
        upTextButton.setText(keyDecoder(keyCode));
    }

    public TextButton getMainMenu() {
        return mainMenu;
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

}
