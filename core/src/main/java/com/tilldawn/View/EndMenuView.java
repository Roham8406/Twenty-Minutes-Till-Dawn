package com.tilldawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.Control.EndMenuController;
import com.tilldawn.Main;
import com.tilldawn.Model.Player;

public class EndMenuView implements Screen {
    private Stage stage;
    private final TextButton won;
    private final boolean isWon;
    private final TextButton kills;
    private final TextButton xp;
    private final TextButton playtime;
    private final TextButton mainMenu;
    private final Player player;
    private final Float playTime;
    public Table table;
    private final EndMenuController controller;

    public EndMenuView(EndMenuController controller, Skin skin, boolean won, Player player) {
        this.controller = controller;
        this.isWon = won;
        this.won = new TextButton(won ? Main.getLanguage().Congrats : Main.getLanguage().Loser, skin);
        this.kills = new TextButton(Main.getLanguage().Kills + ": " + player.getKills(), skin);
        this.playTime = Main.getMain().getGame().getTimer().getDuration()
            - Main.getMain().getGame().getTimer().getRemaining();
        this.playtime = new TextButton("" + Math.floor(playTime), skin);
        this.xp = new TextButton(Main.getLanguage().Score + ": " + player.getKills() * this.playTime, skin);
        this.mainMenu = new TextButton(Main.getLanguage().ClickToEnd, skin);
        this.player = player;

        this.table = new Table();

        controller.setView(this);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table.setFillParent(true);
        table.center();

        table.add(won).width(800);
        table.row().pad(10, 0, 10, 0);
        table.add(kills).width(400);
        table.row().pad(10, 0, 10, 0);
        table.add(xp).width(400);
        table.row().pad(10, 0, 10, 0);
        table.add(playtime).width(400);
        table.row().pad(10, 0, 10, 0);
        table.add(mainMenu).width(400);

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

    public TextButton getMainMenu() {
        return mainMenu;
    }

    public Stage getStage() {
        return stage;
    }

    public Player getPlayer() {
        return player;
    }

    public int getPlayTime() {
        return (int) Math.floor(playTime);
    }
}
