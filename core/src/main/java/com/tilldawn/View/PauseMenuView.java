package com.tilldawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.Control.AbilityMenuController;
import com.tilldawn.Control.GameController;
import com.tilldawn.Control.PauseMenuController;
import com.tilldawn.Main;

public class PauseMenuView implements Screen {
    private Stage stage;
    private final TextButton resume;
    private final ImageButton timeDiscount;
    private final ImageButton hpIncrement;
    private final ImageButton levelIncrement;
    private final ImageButton bossFight;
    private final ImageButton sth;
    private final TextButton vitality;
    private final TextButton damager;
    private final TextButton procrease;
    private final TextButton amocrease;
    private final TextButton speedy;
    private final TextButton giveUp;
    private final TextButton blackAndWhite;
    private final TextButton save;
    public Table table;
    private final GameController gameController;
    private final PauseMenuController controller;
    private final Skin skin;

    public PauseMenuView(PauseMenuController controller, Skin skin, GameController gameController) {
        this.skin = skin;
        this.controller = controller;
        this.gameController = gameController;
        this.resume = new TextButton(Main.getLanguage().Resume, skin);
        this.save = new TextButton(Main.getLanguage().Save, skin);
        this.giveUp = new TextButton(Main.getLanguage().GiveUp, skin);
        this.blackAndWhite = new TextButton(Main.getLanguage().Grayscale, skin);
        Texture buttonTexture = new Texture(Gdx.files.internal("T/T_LevelUpFX_8.png"));
        TextureRegionDrawable drawable = new TextureRegionDrawable(new TextureRegion(buttonTexture));
        this.levelIncrement = new ImageButton(drawable);
        buttonTexture = new Texture(Gdx.files.internal("T/T_HasturBossWindup_0.png"));
        drawable = new TextureRegionDrawable(new TextureRegion(buttonTexture));
        this.bossFight = new ImageButton(drawable);
        buttonTexture = new Texture(Gdx.files.internal("T/T_HeartPickup.png"));
        drawable = new TextureRegionDrawable(new TextureRegion(buttonTexture));
        this.hpIncrement = new ImageButton(drawable);
        buttonTexture = new Texture(Gdx.files.internal("T/T_ShockwaveFX_0.png"));
        drawable = new TextureRegionDrawable(new TextureRegion(buttonTexture));
        this.timeDiscount = new ImageButton(drawable);
        buttonTexture = new Texture(Gdx.files.internal("T/T_LargeChestAnimation_2.png"));
        drawable = new TextureRegionDrawable(new TextureRegion(buttonTexture));
        this.sth = new ImageButton(drawable);
        this.amocrease = new TextButton(Main.getMain().getGame().getAmocrease().toString(), skin);
        this.procrease = new TextButton(Main.getMain().getGame().getAmocrease().toString(), skin);
        this.speedy = new TextButton(Main.getMain().getGame().getSpeedy().toString(), skin);
        this.damager = new TextButton(Main.getMain().getGame().getDamager().toString(), skin);
        this.vitality = new TextButton(Main.getMain().getGame().getVitality().toString(), skin);

        this.table = new Table();

        controller.setView(this);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table.setFillParent(true);
        table.center();

        table.add(new Label(Main.getLanguage().Vitality, skin)).colspan(2).width(250).padRight(30);
        table.add(new Label(Main.getLanguage().Procrease, skin)).colspan(2).width(250).padRight(30);
        table.add(new Label(Main.getLanguage().Amocrease, skin)).colspan(2).width(250).padRight(30);
        table.add(new Label(Main.getLanguage().Damager, skin)).colspan(2).width(250).padRight(30);
        table.add(new Label(Main.getLanguage().Speedy, skin)).colspan(2).width(250);
        table.row().pad(10, 0 , 10, 0);
        table.add(vitality).colspan(2).width(250).padRight(30);
        table.add(procrease).colspan(2).width(250).padRight(30);
        table.add(amocrease).colspan(2).width(250).padRight(30);
        table.add(damager).colspan(2).width(250).padRight(30);
        table.add(speedy).colspan(2).width(250);
        table.row().pad(10, 0 , 10, 0);
        table.add(resume).colspan(5).width(300).padRight(30);
        table.add(blackAndWhite).colspan(5).width(300);
        table.row().pad(10, 0 , 10, 0);
        table.add(save).colspan(5).width(300).padRight(30);
        table.add(giveUp).colspan(5).width(300);
        table.row().pad(10, 0 , 10, 0);
        table.add(timeDiscount).colspan(2).width(50).padRight(30);
        table.add(hpIncrement).colspan(2).width(50).padRight(30);
        table.add(levelIncrement).colspan(2).width(50).padRight(30);
        table.add(bossFight).colspan(2).width(50).padRight(30);
        table.add(sth).colspan(2).width(50);
        table.row().pad(10, 0 , 10, 0);
        table.add(new Label("Num 1", skin)).colspan(2).width(50).padRight(30);
        table.add(new Label("Num 2", skin)).colspan(2).width(50).padRight(30);
        table.add(new Label("Num 3", skin)).colspan(2).width(50).padRight(30);
        table.add(new Label("Num 4", skin)).colspan(2).width(50).padRight(30);
        table.add(new Label("Num 5", skin)).colspan(2).width(50);
        table.row().pad(10, 0 , 10, 0);

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

    public GameController getGameController() {
        return gameController;
    }

    public ImageButton getLevelIncrement() {
        return levelIncrement;
    }

    public Stage getStage() {
        return stage;
    }

    public ImageButton getBossFight() {
        return bossFight;
    }

    public ImageButton getHpIncrement() {
        return hpIncrement;
    }

    public ImageButton getTimeDiscount() {
        return timeDiscount;
    }

    public ImageButton getSth() {
        return sth;
    }

    public TextButton getResume() {
        return resume;
    }

    public TextButton getBlackAndWhite() {
        return blackAndWhite;
    }

    public TextButton getGiveUp() {
        return giveUp;
    }

    public TextButton getSave() {
        return save;
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
