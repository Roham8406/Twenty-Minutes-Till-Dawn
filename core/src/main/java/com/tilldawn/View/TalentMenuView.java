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
    private final TextButton hero1;
    private final TextButton hero2;
    private final TextButton hero3;
    private final TextButton hero4;
    private final TextButton hero5;
    private final TextButton cheatCode1;
    private final TextButton cheatCode2;
    private final TextButton cheatCode3;
    private final TextButton cheatCode4;
    private final TextButton cheatCode5;
    private final TextButton ability1;
    private final TextButton ability2;
    private final TextButton ability3;
    private final TextButton ability4;
    private final TextButton ability5;
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
        this.hero1 = new TextButton("Shana, speed: 4, Hp: 4", skin);
        this.hero2 = new TextButton("Diamond, speed: 1, Hp: 7", skin);
        this.hero3 = new TextButton("Scarlett, speed: 5, Hp: 3", skin);
        this.hero4 = new TextButton("Lilith, speed: 3, Hp: 5", skin);
        this.hero5 = new TextButton("Dasher, speed: 10, Hp: 2", skin);
        this.cheatCode1 = new TextButton("1: Pass 1min", skin);
        this.cheatCode2 = new TextButton("2: Add Hp", skin);
        this.cheatCode3 = new TextButton("3: Next Level", skin);
        this.cheatCode4 = new TextButton("4: Boss Fight", skin);
        this.cheatCode5 = new TextButton("5: Fast Reload", skin);
        this.ability1 = new TextButton("Vitality: Increase Maximum HP", skin);
        this.ability2 = new TextButton("Damager: 25% more strong for 10s", skin);
        this.ability3 = new TextButton("Procrease: Increase Projectile", skin);
        this.ability4 = new TextButton("Amocrease: 5 more strong ammo", skin);
        this.ability5 = new TextButton("Speedy: Twice faster for 10s", skin);
        this.upLabel = new Label(Main.getLanguage().Up, skin);
        this.downLabel = new Label(Main.getLanguage().Down, skin);
        this.leftLabel = new Label(Main.getLanguage().Left, skin);
        this.rightLabel = new Label(Main.getLanguage().Right, skin);
        this.autoAimLabel = new Label(Main.getLanguage().AutoAim, skin);
        this.reloadLabel = new Label(Main.getLanguage().Reload, skin);
        Function<String, String> keyDecoder = key ->
            Input.Keys.toString(Main.getMain().getControl().getKeys().get(key));
        this.upTextButton = new TextButton(keyDecoder.apply("up"), skin);
        this.downTextButton = new TextButton(keyDecoder.apply("down"), skin);
        this.leftTextButton = new TextButton(keyDecoder.apply("left"), skin);
        this.rightTextButton = new TextButton(keyDecoder.apply("right"), skin);
        this.autoAimTextButton = new TextButton(keyDecoder.apply("autoAim"), skin);
        this.reloadTextButton = new TextButton(keyDecoder.apply("reload"), skin);
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

        table.add(hero1).colspan(2).width(600);
        table.add(hero2).colspan(2).width(600);
        table.add(hero3).colspan(2).width(600);
//        table.add(hero4).colspan(1).width(400);
//        table.add(hero5).colspan(1).width(400);
        table.row().pad(10, 0 , 10, 0);
        table.add(cheatCode1).colspan(1).width(300);
        table.add(cheatCode2).colspan(1).width(300);
        table.add(cheatCode3).colspan(1).width(300);
        table.add(cheatCode4).colspan(1).width(300);
        table.add(cheatCode5).colspan(1).width(300);
        table.row().pad(10, 0 , 10, 0);
        table.add(ability1).colspan(6).width(900);
        table.row().pad(10, 0 , 10, 0);
        table.add(ability2).colspan(3).width(900).padRight(35);
        table.add(ability3).colspan(3).width(900);
        table.row().pad(10, 0 , 10, 0);
        table.add(ability4).colspan(3).width(900).padRight(35);
        table.add(ability5).colspan(3).width(900);
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
        table.add(mainMenu).colspan(6).width(600);

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

    public Stage getStage() {
        return stage;
    }

    public TextButton getMainMenu() {
        return mainMenu;
    }
}
