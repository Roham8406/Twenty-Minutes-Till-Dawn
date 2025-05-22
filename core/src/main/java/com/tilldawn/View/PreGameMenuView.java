package com.tilldawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.Control.GameController;
import com.tilldawn.Control.PreGameMenuController;
import com.tilldawn.Main;
import com.tilldawn.Model.GameCharacter;
import com.tilldawn.Model.WeaponType;
import org.w3c.dom.Text;

import java.awt.font.ImageGraphicAttribute;

public class PreGameMenuView implements Screen {

    private Stage stage;
    private final ImageButton shana;
    private final ImageButton diamond;
    private final ImageButton scarlet;
    private final ImageButton lilith;
    private final ImageButton dasher;
    private final ImageButton revolver;
    private final ImageButton shotgun;
    private final ImageButton smg;
    private final TextButton time2;
    private final TextButton time5;
    private final TextButton time10;
    private final TextButton time20;
    private final TextButton start;
    public Table table;
    private Skin skin;
    private PreGameMenuController controller;

    public PreGameMenuView(PreGameMenuController controller, Skin skin) {
        this.shana = GameCharacter.Shana.getPortraitButton();
        this.diamond = GameCharacter.Diamond.getPortraitButton();
        this.scarlet = GameCharacter.Scarlet.getPortraitButton();
        this.lilith = GameCharacter.Lilith.getPortraitButton();
        this.dasher = GameCharacter.Dasher.getPortraitButton();
        this.revolver = WeaponType.Revolver.getPortraitButton();
        this.shotgun = WeaponType.Shotgun.getPortraitButton();
        this.smg = WeaponType.Smg.getPortraitButton();
        this.time2 = new TextButton("2 Minutes", skin);
        this.time5 = new TextButton("5 Minutes", skin);
        this.time10 = new TextButton("10 Minutes", skin);
        this.time20 = new TextButton("20 Minutes", skin);
        this.start = new TextButton("Start", skin);
        this.table = new Table();
        this.skin = skin;
        this.controller = controller;
        controller.setView(this);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        table.center();
        table.add(shana).colspan(12).height(100).padRight(30);
        table.add(diamond).colspan(12).height(100).padRight(30);
        table.add(scarlet).colspan(12).height(100).padRight(30);
        table.add(lilith).colspan(12).height(100).padRight(30);
        table.add(dasher).colspan(12).height(100);
        table.row().pad(10, 0 , 10 , 0);
        table.setFillParent(true);
        table.add(revolver).colspan(20).height(100).padRight(30);
        table.add(shotgun).colspan(20).height(100).padRight(30);
        table.add(smg).colspan(20).height(100);
        table.row().pad(10, 0 , 10 , 0);
        table.add(time2).colspan(15).width(300).padRight(30);
        table.add(time5).colspan(15).width(300).padRight(30);
        table.add(time10).colspan(15).width(300).padRight(30);
        table.add(time20).colspan(15).width(300);
        table.row().pad(10, 0 , 10 , 0);
        table.add(start).colspan(60).width(400);

        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.2f, 0.5f, 0.4f, 1);
        Main.getBatch().begin();
        Main.getBatch().end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        controller.handlePreGameMenuButtons();
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

    public ImageButton getDasher() {
        return dasher;
    }

    public ImageButton getDiamond() {
        return diamond;
    }

    public ImageButton getLilith() {
        return lilith;
    }

    public ImageButton getRevolver() {
        return revolver;
    }

    public ImageButton getScarlet() {
        return scarlet;
    }

    public ImageButton getShana() {
        return shana;
    }

    public ImageButton getShotgun() {
        return shotgun;
    }

    public ImageButton getSmg() {
        return smg;
    }

    public TextButton getStart() {
        return start;
    }

    public TextButton getTime2() {
        return time2;
    }

    public TextButton getTime5() {
        return time5;
    }

    public TextButton getTime10() {
        return time10;
    }

    public TextButton getTime20() {
        return time20;
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
