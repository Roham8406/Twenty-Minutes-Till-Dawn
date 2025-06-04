package com.tilldawn.Control;

import com.tilldawn.Main;
import com.tilldawn.Model.GameAssetManager;
import com.tilldawn.View.MainMenuView;
import com.tilldawn.View.ScoreboardMenuView;

public class ScoreboardMenuController extends MenuController {
    private ScoreboardMenuView view;

    public void setView(ScoreboardMenuView view) {
        this.view = view;
    }

    public void handleMainMenuButtons() {
        addClickSoundToButtons(view.getStage().getRoot());
        if (view != null) {
            if (view.getKill().isChecked()) {
                Main.getMain().getScreen().dispose();
                ScoreboardMenuView scoreboardMenuView = new ScoreboardMenuView(new ScoreboardMenuController(), GameAssetManager.getGameAssetManager().getSkin());
                scoreboardMenuView.setScoreName("kills");
                Main.getMain().setScreen(scoreboardMenuView);
                view.getKill().setChecked(false);
                return;
            }
            if (view.getUsername().isChecked()) {
                Main.getMain().getScreen().dispose();
                ScoreboardMenuView scoreboardMenuView = new ScoreboardMenuView(new ScoreboardMenuController(), GameAssetManager.getGameAssetManager().getSkin());
                scoreboardMenuView.setScoreName("username");
                Main.getMain().setScreen(scoreboardMenuView);
                view.getUsername().setChecked(false);
                return;
            }
            if (view.getScore().isChecked()) {
                Main.getMain().getScreen().dispose();
                ScoreboardMenuView scoreboardMenuView = new ScoreboardMenuView(new ScoreboardMenuController(), GameAssetManager.getGameAssetManager().getSkin());
                scoreboardMenuView.setScoreName("score");
                Main.getMain().setScreen(scoreboardMenuView);
                view.getScore().setChecked(false);
                return;
            }
            if (view.getPlaytime().isChecked()) {
                Main.getMain().getScreen().dispose();
                ScoreboardMenuView scoreboardMenuView = new ScoreboardMenuView(new ScoreboardMenuController(), GameAssetManager.getGameAssetManager().getSkin());
                scoreboardMenuView.setScoreName("playtime");
                Main.getMain().setScreen(scoreboardMenuView);
                view.getPlaytime().setChecked(false);
                return;
            }
            if (view.getMainMenu().isChecked()) {
                Main.getMain().getScreen().dispose();
                MainMenuView mainMenuView = new MainMenuView(new MainMenuController(), GameAssetManager.getGameAssetManager().getSkin());
                Main.getMain().setScreen(mainMenuView);
                view.getKill().setChecked(false);
            }

        }
    }
}
