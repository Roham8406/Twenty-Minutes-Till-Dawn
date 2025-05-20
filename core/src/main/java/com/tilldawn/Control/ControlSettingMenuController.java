package com.tilldawn.Control;

import com.tilldawn.View.ControlSettingsMenuView;
import com.tilldawn.View.SettingsMenuView;

public class ControlSettingMenuController extends MenuController {
    private ControlSettingsMenuView view;

    public void setView(ControlSettingsMenuView view) {
        this.view = view;
    }

    public void handleMainMenuButtons() {
        addClickSoundToButtons(view.getStage().getRoot());
        if (view != null) {
        }
    }
}
