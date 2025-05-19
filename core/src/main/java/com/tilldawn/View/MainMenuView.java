package com.tilldawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.Control.MainMenuController;
import com.tilldawn.Main;
import com.tilldawn.Model.User;

public class MainMenuView implements Screen {
    private Stage stage;
    private final TextButton setting;
    private final TextButton profile;
    private final TextButton pregame;
    private final TextButton scoreboard;
    private final TextButton talent;
    private final TextButton loadGame;
    public Table table;
    private final Table header;
    private final MainMenuController controller;

    public MainMenuView(MainMenuController controller, Skin skin) {
        this.controller = controller;
        this.setting = new TextButton("Setting Menu", skin);
        this.pregame = new TextButton("Pre-Game Menu", skin);
        this.scoreboard = new TextButton("Scoreboard Menu", skin);
        this.talent = new TextButton("Talent Menu", skin);
        this.table = new Table();
        if (Main.getMain().getCurrentUser() == null) {
            this.header = User.createUnloggedHeader(skin);
            this.profile = new TextButton("Logout", skin);
            this.loadGame = new TextButton("Login Menu", skin);
        } else {
            this.header = Main.getMain().getCurrentUser().createHeader(skin);
            this.loadGame = new TextButton("Load Game", skin);
            this.profile = new TextButton("Profile Menu", skin);
        }

        controller.setView(this);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table.setFillParent(true);
        table.top();

        table.add(header).height(200).colspan(2); // Set height here
        table.row().pad(200, 0 , 10 , 0);
        table.add(setting).width(500).padRight(30);
        table.add(talent).width(500);
        table.row().pad(10, 0 , 10, 0);
        table.add(pregame).width(500).padRight(30);
        table.add(scoreboard).width(500);
        table.row().pad(10, 0 , 10 , 0);
        table.add(profile).width(500).padRight(30);
        table.add(loadGame).width(500);

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

    public TextButton getPlayButton() {
        return setting;
    }

}
