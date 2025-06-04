package com.tilldawn.Control;

import com.tilldawn.Main;
import com.tilldawn.Model.GameAssetManager;
import com.tilldawn.Model.User;
import com.tilldawn.View.EndMenuView;
import com.tilldawn.View.MainMenuView;

public class EndMenuController extends MenuController {
    private EndMenuView view;

    public void setView(EndMenuView view) {
        this.view = view;
    }

    public void handleMainMenuButtons() {
        addClickSoundToButtons(view.getStage().getRoot());
        if (view != null) {
            if (view.getMainMenu().isChecked()) {
                User user = Main.getMain().getCurrentUser();
                if (user != null) {
                    user.addKills(view.getPlayer().getKills());
                    user.addPlaytime(view.getPlayTime());
                    user.addScore(view.getPlayer().getKills() * view.getPlayTime());
                    Main.getMain().getUserSql().updateStats(user);
                }
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new MainMenuView(new MainMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
        }
    }
}
