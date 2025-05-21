package com.tilldawn.Control;

import com.tilldawn.Main;
import com.tilldawn.Model.GameAssetManager;
import com.tilldawn.View.MainMenuView;
import com.tilldawn.View.TalentMenuView;

public class TalentMenuController extends MenuController {
    private TalentMenuView view;

    public void setView(TalentMenuView view) {
        this.view = view;
    }

    public void handleMainMenuButtons() {
        addClickSoundToButtons(view.getStage().getRoot());
        if (view != null) {
            if (view.getMainMenu().isChecked()) {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new MainMenuView(new MainMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
        }
    }
}
