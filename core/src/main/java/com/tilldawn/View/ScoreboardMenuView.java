package com.tilldawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.Control.ScoreboardMenuController;
import com.tilldawn.Main;
import com.tilldawn.Model.User;

import java.util.ArrayList;

public class ScoreboardMenuView implements Screen {
    private Stage stage;
    private final TextButton username;
    private final TextButton kill;
    private final TextButton playtime;
    private final TextButton score;
    private Skin skin;
    private final TextButton mainMenu;
    public Table table;
    private String scoreName = "username";
    private final ScoreboardMenuController controller;

    public ScoreboardMenuView(ScoreboardMenuController controller, Skin skin) {
        this.controller = controller;
        this.skin = skin;
        this.kill = new TextButton(Main.getLanguage().Kills, skin);
        this.playtime = new TextButton(Main.getLanguage().Playtime, skin);
        this.score = new TextButton(Main.getLanguage().Score, skin);
        this.username = new TextButton(Main.getLanguage().Username, skin);
        this.mainMenu = new TextButton(Main.getLanguage().MainMenu, skin);
        this.table = new Table(skin);
        controller.setView(this);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table.setFillParent(true);
        table.center();

        table.add(username).width(400).padRight(40);
        table.add(kill).width(200).padRight(40);
        table.add(score).width(200).padRight(40);
        table.add(playtime).width(200).padRight(40);
        table.row().pad(40, 0 , 10 , 0);
        ArrayList<User> users = Main.getMain().getUserSql().sortScoreboard(scoreName);
        if (users == null) {
            alert(Main.getLanguage().SomethingWentWrong, 5);
            return;
        }
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            Label label;
            if (Main.getMain().getCurrentUser() != null &&
                user.getUsername().equals(Main.getMain().getCurrentUser().getUsername())) {
                Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
                pixmap.setColor(Color.BLUE);
                pixmap.fill();
                Texture texture = new Texture(pixmap);
                Drawable background = new TextureRegionDrawable(new TextureRegion(texture));
                Label.LabelStyle style = new Label.LabelStyle();
                style.font = new BitmapFont(Gdx.files.internal("Fonts/Harrington.fnt"));
                style.fontColor = Color.WHITE;
                style.background = background;

                label = new Label(user.toString(), style);
            } else {
                label = new Label(user.toString(), skin);
            }
            switch (i) {
                case 0:
                    label.setColor(0, 0.5f, 0.5f, 1);
                    break;
                case 1:
                    label.setColor(0.5f, 0.5f, 0.5f, 1);
                    break;
                case 2:
                    label.setColor(0.5f, 0, 0, 1);
            }
            table.add(label).colspan(4).width(1160);
            table.row().pad(10, 0 , 10, 0);
        }

        table.add(mainMenu).colspan(4).width(500);

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

    public void setScoreName(String scoreName) {
        this.scoreName = scoreName;
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

    public TextButton getKill() {
        return kill;
    }

    public TextButton getPlaytime() {
        return playtime;
    }

    public TextButton getScore() {
        return score;
    }

    public TextButton getUsername() {
        return username;
    }

    public TextButton getMainMenu() {
        return mainMenu;
    }
}
