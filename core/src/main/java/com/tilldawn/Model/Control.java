package com.tilldawn.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.tilldawn.Control.ControlSettingMenuController;
import com.tilldawn.Control.SettingMenuController;
import com.tilldawn.Main;
import com.tilldawn.View.ControlSettingsMenuView;
import com.tilldawn.View.SettingsMenuView;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Control implements Serializable {
    private static final long serialVersionUID = 1L;
    private final Map<String, Integer> keys = new HashMap<>();

    public Control() {
        keys.put("up", Input.Keys.W);
        keys.put("down", Input.Keys.S);
        keys.put("left", Input.Keys.A);
        keys.put("right", Input.Keys.D);
        keys.put("autoAim", Input.Keys.SPACE);
        keys.put("reload", Input.Keys.R);
    }

    public Integer changeUp(ControlSettingMenuController stage) {
        changeKey(stage, "up");
        return keys.get("up");
    }

    public Integer changeDown(ControlSettingMenuController stage) {
        changeKey(stage, "down");
        return keys.get("down");
    }

    public Integer changeRight(ControlSettingMenuController stage) {
        changeKey(stage, "right");
        return keys.get("right");
    }

    public Integer changeLeft(ControlSettingMenuController stage) {
        changeKey(stage, "left");
        return keys.get("left");
    }

    public Integer changeAutoAim(ControlSettingMenuController stage) {
        changeKey(stage, "autoAim");
        return keys.get("autoAim");
    }

    public Integer changeReload(ControlSettingMenuController stage) {
        changeKey(stage, "reload");
        return keys.get("reload");
    }

    private void changeKey(ControlSettingMenuController stage, String key) {

        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keycode) {
                keys.put(key, keycode);
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new ControlSettingsMenuView(new ControlSettingMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
                return true;
            }
        });
    }

    public Map<String, Integer> getKeys() {
        return keys;
    }
}
