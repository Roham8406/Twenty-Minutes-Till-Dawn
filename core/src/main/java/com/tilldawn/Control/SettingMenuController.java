package com.tilldawn.Control;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.tilldawn.Main;
import com.tilldawn.Model.GameAssetManager;
import com.tilldawn.View.ControlSettingsMenuView;
import com.tilldawn.View.MainMenuView;
import com.tilldawn.View.SettingsMenuView;

public class SettingMenuController extends MenuController {
    private SettingsMenuView view;

    public void setView(SettingsMenuView view) {
        this.view = view;
    }

    public void handleMainMenuButtons() {
        addClickSoundToButtons(view.getStage().getRoot());
        if (view != null) {
            view.getMusicVolume().addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    Main.getMain().setMusicVolume(view.getMusicVolume().getValue());
                }
            });
            view.getMusicTrack().addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    Main.getMain().setMusic(view.getMusicTrack().getSelected());
                }
            });
            view.getLanguage().addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    Main.getMain().setLanguage(view.getLanguage().getSelected());
                    Main.getMain().getScreen().dispose();
                    Main.getMain().setScreen(new SettingsMenuView(new SettingMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
                }
            });
            view.getSfx().addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    Main.getMain().setSfx(view.getSfx().isChecked());
                }
            });
            view.getAutoReload().addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    Main.getMain().setAutoReload(view.getAutoReload().isChecked());
                }
            });
            view.getBlackAndWhite().addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    Main.getMain().setBlackAndWhite(view.getBlackAndWhite().isChecked());
                }
            });
            if (view.getControlSettings().isChecked()) {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new ControlSettingsMenuView(new ControlSettingMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
            if (view.getMainMenu().isChecked()) {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new MainMenuView(new MainMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
        }
    }
}
