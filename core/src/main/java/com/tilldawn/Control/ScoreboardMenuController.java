package com.tilldawn.Control;

import com.tilldawn.View.LoginMenuView;
import com.tilldawn.View.ScoreboardMenuView;

public class ScoreboardMenuController extends MenuController {
    private ScoreboardMenuView view;

    public void setView(ScoreboardMenuView view) {
        this.view = view;
    }

    public void handleMainMenuButtons() {
        addClickSoundToButtons(view.getStage().getRoot());
        if (view != null) {

        }
    }
}
