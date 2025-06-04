package com.tilldawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.tilldawn.Control.GameController;
import com.tilldawn.Control.WeaponController;
import com.tilldawn.Main;
import com.tilldawn.Model.AnimatedSprite;

public class GameView implements Screen, InputProcessor {
    private Stage stage;
    private GameController controller;
    private Skin skin;
    private Cursor cursor;


    public GameView(GameController controller, Skin skin) {
        this.controller = controller;
        this.skin = skin;
        controller.setView(this);
        Pixmap pixmap = new Pixmap(Gdx.files.internal("T/T_Cursor.png"));
        cursor = Gdx.graphics.newCursor(pixmap, 16, 16);
        pixmap.dispose();
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(this);
        Gdx.graphics.setCursor(cursor);
        Main.getMain().getGame().getTimer().start();
    }

    @Override
    public void render(float delta) {
        Main.getMain().startBatch();
        ScreenUtils.clear(0, 0, 0, 1);
        controller.updateGame(delta);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        Main.getMain().getGame().getTimer().update(delta);
        stage.draw();
        Main.getMain().endBatch();
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

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        controller.getWeaponController().handleWeaponRotation(screenX, screenY);
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }

    public Stage getStage() {
        return stage;
    }

    public Skin getSkin() {
        return skin;
    }

    public GameController getController() {
        return controller;
    }
}
