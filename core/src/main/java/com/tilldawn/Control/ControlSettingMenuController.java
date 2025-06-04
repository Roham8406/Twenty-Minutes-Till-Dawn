package com.tilldawn.Control;

import com.tilldawn.Main;
import com.tilldawn.Model.GameAssetManager;
import com.tilldawn.View.ControlSettingsMenuView;
import com.tilldawn.View.MainMenuView;

import java.util.ArrayList;
import java.util.Map;

public class ControlSettingMenuController extends MenuController {
    private ControlSettingsMenuView view;

    public void setView(ControlSettingsMenuView view) {
        this.view = view;
    }

    public void handleMainMenuButtons() {
        addClickSoundToButtons(view.getStage().getRoot());
        if (view != null) {
            Map<String, Integer> control = Main.getMain().getControl().getKeys();
            if (view.getAutoAimTextButton().isChecked()) {
                view.setAutoAim();
                view.getAutoAimTextButton().setChecked(false);
            }
            if (view.getDownTextButton().isChecked()) {
                view.setDown();
                view.getUpTextButton().setChecked(false);
            }
            if (view.getLeftTextButton().isChecked()) {
                view.setLeft();
                view.getRightTextButton().setChecked(false);
            }
            if (view.getReloadTextButton().isChecked()) {
                view.setReload();
                view.getReloadTextButton().setChecked(false);
            }
            if (view.getRightTextButton().isChecked()) {
                view.setRight();
                view.getRightTextButton().setChecked(false);
            }
            if (view.getUpTextButton().isChecked()) {
                view.setUpKey();
            }
            if (view.getShootTextButton().isChecked()) {
                view.setShoot();
                view.getShootTextButton().setChecked(false);
            }
            if (view.getMainMenu().isChecked()) {
                ArrayList<Integer> keyCodes = new ArrayList<>();
                for (Integer value : control.values()) {
                    if (keyCodes.contains(value)) {
                        view.alert(Main.getLanguage().SimilarKeysError, 5);
                        view.getMainMenu().setChecked(false);
                        return;
                    }
                    keyCodes.add(value);
                }
                keyCodes.clear();
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new MainMenuView(new MainMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
        }
    }
}
