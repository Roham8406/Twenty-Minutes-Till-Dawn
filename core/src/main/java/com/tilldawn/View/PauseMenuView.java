package com.tilldawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.Control.AbilityMenuController;
import com.tilldawn.Control.GameController;
import com.tilldawn.Control.PauseMenuController;
import com.tilldawn.Main;

public class PauseMenuView implements Screen {
    private Stage stage;
    private final TextButton vitality;
    private final TextButton damager;
    private final TextButton procrease;
    private final TextButton amocrease;
    private final TextButton speedy;
    private final TextButton levelUp;
    public Table table;
    private final GameController gameController;
    private final PauseMenuController controller;

    public PauseMenuView(PauseMenuController controller, Skin skin, GameController gameController, Integer level) {
        this.controller = controller;
        this.gameController = gameController;
        this.vitality = new TextButton("Vitality", skin);
        this.damager = new TextButton("Damager", skin);
        this.procrease = new TextButton("Procrease", skin);
        this.amocrease = new TextButton("Amocrease", skin);
        this.speedy = new TextButton("Speedy", skin);
        this.levelUp = new TextButton("Next Level: " + level, skin);
        this.table = new Table();

        controller.setView(this);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table.setFillParent(true);
        table.center();

        table.add(levelUp).width(600);
        table.row().pad(10, 0 , 10, 0);
        table.add(vitality).width(400);
        table.row().pad(10, 0 , 10, 0);
        table.add(procrease).width(400);
        table.row().pad(10, 0 , 10, 0);
        table.add(amocrease).width(400);
        table.row().pad(10, 0 , 10, 0);
        table.add(damager).width(400);
        table.row().pad(10, 0 , 10, 0);
        table.add(speedy).width(400);
        table.row().pad(10, 0 , 10, 0);

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

    public TextButton getAmocrease() {
        return amocrease;
    }

    public TextButton getDamager() {
        return damager;
    }

    public TextButton getProcrease() {
        return procrease;
    }

    public GameController getGameController() {
        return gameController;
    }

    public TextButton getLevelUp() {
        return levelUp;
    }

    public TextButton getSpeedy() {
        return speedy;
    }

    public TextButton getVitality() {
        return vitality;
    }

    public Stage getStage() {
        return stage;
    }
}
