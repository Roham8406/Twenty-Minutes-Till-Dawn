package com.tilldawn.Control;

import com.tilldawn.Main;
import com.tilldawn.Model.GameAssetManager;
import com.tilldawn.View.EndMenuView;
import com.tilldawn.View.MainMenuView;
import com.tilldawn.View.PauseMenuView;
import com.tilldawn.service.SaveService;

public class PauseMenuController extends MenuController {
    private PauseMenuView view;

    public void setView(PauseMenuView view) {
        this.view = view;
    }

    public void handleMainMenuButtons() {
        addClickSoundToButtons(view.getStage().getRoot());
        if (view != null) {
            if (view.getResume().isChecked()) {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(view.getGameController().getView());
                view.getResume().setChecked(false);
            }
            if (view.getSave().isChecked()) {
                view.alert(SaveService.getInstance().save().getMessage(), 5);
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new MainMenuView(new MainMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
                view.getSave().setChecked(false);
            }
            if (view.getBlackAndWhite().isChecked()) {
                Main.getMain().setBlackAndWhite(!Main.getMain().isBlackAndWhite());
                view.getBlackAndWhite().setChecked(false);
            }
            if (view.getGiveUp().isChecked()) {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new EndMenuView(new EndMenuController(),
                    GameAssetManager.getGameAssetManager().getSkin(), false,
                    Main.getMain().getGame().getHero()));
                view.getGiveUp().setChecked(false);
            }
        }
    }
}
