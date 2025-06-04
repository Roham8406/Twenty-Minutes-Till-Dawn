package com.tilldawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.Control.AbilityMenuController;
import com.tilldawn.Control.GameController;
import com.tilldawn.Main;

import java.util.ArrayList;
import java.util.Random;

public class AbilityMenuView implements Screen {
    private Stage stage;
    private final TextButton vitality;
    private final TextButton damager;
    private final TextButton procrease;
    private final TextButton amocrease;
    private final TextButton speedy;
    private final TextButton levelUp;
    public Table table;
    private final GameController gameController;
    private final AbilityMenuController controller;
    private final ArrayList<TextButton> abilities = new ArrayList<>();

    public AbilityMenuView(AbilityMenuController controller, Skin skin, GameController gameController, Integer level) {
        this.controller = controller;
        this.gameController = gameController;
        this.vitality = new TextButton(Main.getLanguage().Vitality, skin);
        this.damager = new TextButton(Main.getLanguage().Damager, skin);
        this.procrease = new TextButton(Main.getLanguage().Procrease, skin);
        this.amocrease = new TextButton(Main.getLanguage().Amocrease, skin);
        this.speedy = new TextButton(Main.getLanguage().Speedy, skin);
        this.levelUp = new TextButton(Main.getLanguage().NextLevel + ": " + level, skin);
        this.table = new Table();
        abilities.add(vitality);
        abilities.add(damager);
        abilities.add(procrease);
        abilities.add(amocrease);
        abilities.add(speedy);
        Random random = new Random();
        abilities.remove(random.nextInt(abilities.size()));
        abilities.remove(random.nextInt(abilities.size()));
        abilities.add(0, levelUp);

        controller.setView(this);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table.setFillParent(true);
        table.center();

        for (TextButton ability : abilities) {
            table.add(ability).width(600);
            table.row().pad(10, 0, 10, 0);
        }

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
