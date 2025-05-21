package com.tilldawn.Control;

import com.tilldawn.Main;
import com.tilldawn.Model.GameAssetManager;
import com.tilldawn.View.*;

public class MainMenuController extends MenuController {
    private MainMenuView view;

    public void setView(MainMenuView view) {
        this.view = view;
    }

    public void handleMainMenuButtons() {
        addClickSoundToButtons(view.getStage().getRoot());
        if (view != null) {
            if (view.getSetting().isChecked()) {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new SettingsMenuView(new SettingMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            } else if (view.getTalent().isChecked()) {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new TalentMenuView(new TalentMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            } else if (view.getPregame().isChecked()) {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new PreGameMenuView(new PreGameMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            } else if (view.getScoreboard().isChecked()) {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new ScoreboardMenuView(new ScoreboardMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            } else if (view.getProfile().isChecked()) {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new ProfileMenuView(new ProfileMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            } else if (view.getLogging().isChecked()) {
                if (Main.getMain().getCurrentUser() != null) {
                    Main.getMain().setCurrentUser(null);
                    Main.getMain().getScreen().dispose();
                    Main.getMain().setScreen(new MainMenuView(new MainMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
                } else {
                    Main.getMain().getScreen().dispose();
                    Main.getMain().setScreen(new LoginMenuView(new LoginMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
                }
            }
        }
    }
}
