package com.tilldawn.Control;

import com.tilldawn.Main;
import com.tilldawn.Model.GameAssetManager;
import com.tilldawn.View.*;

public class MainMenuController {
    private MainMenuView view;

    public void setView(MainMenuView view) {
        this.view = view;
    }

    public void handleMainMenuButtons() {
        if (view != null) {
            if (view.getSetting().isChecked()) {
                Main.getMain().getScreen().dispose();
//                Main.getMain().setScreen(new PreGameMenuView(new PreGameMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
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
            } else if (view.getLoadGame().isChecked()) {
                if (Main.getMain().getCurrentUser() != null) {
                    //logout logic
                } else {
                    Main.getMain().getScreen().dispose();
                    Main.getMain().setScreen(new TalentMenuView(new TalentMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
                }
            }
        }
    }
}
