package com.tilldawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.Control.MainMenuController;
import com.tilldawn.Control.TalentMenuController;
import com.tilldawn.Main;
import com.tilldawn.Model.User;

import java.util.function.Function;

public class TalentMenuView implements Screen {
    private Stage stage;
    private Skin skin;
    private final Label hero1;
    private final Label hero2;
    private final Label hero3;
    private final Label cheatCode;
    private final Label ability;
    private final Label upLabel;
    private final Label downLabel;
    private final Label leftLabel;
    private final Label rightLabel;
    private final Label autoAimLabel;
    private final Label reloadLabel;
    private final TextButton upTextButton;
    private final TextButton downTextButton;
    private final TextButton leftTextButton;
    private final TextButton rightTextButton;
    private final TextButton autoAimTextButton;
    private final TextButton reloadTextButton;
    private final TextButton mainMenu;
    public Table table;
    private final TalentMenuController controller;

    public TalentMenuView(TalentMenuController controller, Skin skin) {
        this.controller = controller;
        this.skin = skin;
        this.hero1 = new Label("Hero1 Information", skin); //todo
        this.hero2 = new Label("Hero2 Information", skin); //todo
        this.hero3 = new Label("Hero3 Information", skin); //todo
        this.cheatCode = new Label("Cheat code information", skin); //todo
        this.ability = new Label("Abilities information", skin); //todo
        this.upLabel = new Label("Up", skin);
        this.downLabel = new Label("Down", skin);
        this.leftLabel = new Label("Left", skin);
        this.rightLabel = new Label("Right", skin);
        this.autoAimLabel = new Label("Auto Aim", skin);
        this.reloadLabel = new Label("Reload", skin);
        Function<String, String> keyDecoder = key ->
            Input.Keys.toString(Main.getMain().getControl().getKeys().get(key));
        this.upTextButton = new TextButton(keyDecoder.apply("up"), skin);
        this.downTextButton = new TextButton(keyDecoder.apply("down"), skin);
        this.leftTextButton = new TextButton(keyDecoder.apply("left"), skin);
        this.rightTextButton = new TextButton(keyDecoder.apply("right"), skin);
        this.autoAimTextButton = new TextButton(keyDecoder.apply("autoAim"), skin);
        this.reloadTextButton = new TextButton(keyDecoder.apply("reload"), skin);
        this.mainMenu = new TextButton("Main Menu", skin);
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

        table.add(hero1).colspan(6).width(700);
        table.row().pad(10, 0 , 10, 0);
        table.add(hero2).colspan(6).width(700);
        table.row().pad(10, 0 , 10, 0);
        table.add(hero3).colspan(6).width(700);
        table.row().pad(10, 0 , 10, 0);
        table.add(cheatCode).colspan(6).width(700);
        table.row().pad(10, 0 , 10, 0);
        table.add(ability).colspan(6).width(700);
        table.row().pad(10, 0 , 10, 0);
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
        table.add(mainMenu).colspan(6).width(500);

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

    public Stage getStage() {
        return stage;
    }

    public TextButton getMainMenu() {
        return mainMenu;
    }
}
