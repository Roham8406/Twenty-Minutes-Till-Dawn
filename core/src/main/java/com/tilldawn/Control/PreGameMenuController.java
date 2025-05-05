package com.tilldawn.Control;

import com.tilldawn.Main;
import com.tilldawn.Model.GameAssetManager;
import com.tilldawn.Model.Pregame;
import com.tilldawn.View.PreGameMenuView;

public class PreGameMenuController {
    private PreGameMenuView view;
    private Pregame pregame;


    public void setView(PreGameMenuView view) {
        this.view = view;
        this.pregame = new Pregame();
    }

    public void handlePreGameMenuButtons() {
        if (view != null) {

        }
    }

}
