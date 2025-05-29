package com.tilldawn.Control;

import com.tilldawn.Main;
import com.tilldawn.Model.GameAssetManager;
import com.tilldawn.View.AbilityMenuView;
import com.tilldawn.View.EndMenuView;
import com.tilldawn.View.MainMenuView;
import com.tilldawn.View.PauseMenuView;

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
                //todo save
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new MainMenuView(new MainMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
                view.getSave().setChecked(false);
            }
            if (view.getBlackAndWhite().isChecked()) {
                if (Main.getMain().isBlackAndWhite()) {
                    Main.getMain().setBlackAndWhite(false);
                } else {
                    Main.getMain().setBlackAndWhite(true);
                }
                view.getBlackAndWhite().setChecked(false);
            }
            if (view.getSth().isChecked()) {
                //todo custom cheat code
                view.getSth().setChecked(false);
            }
            if (view.getGiveUp().isChecked()) {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new EndMenuView(new EndMenuController(),
                    GameAssetManager.getGameAssetManager().getSkin(), false,
                    Main.getMain().getGame().getHero()));
                view.getGiveUp().setChecked(false);
            }
            if (view.getBossFight().isChecked()) {
                //todo boss fight
                view.getBossFight().setChecked(false);
            }
            if (view.getHpIncrement().isChecked()) {
                Main.getMain().getGame().getHero().incrementHp(view.getGameController());
                view.getHpIncrement().setChecked(false);
            }
            if (view.getTimeDiscount().isChecked()) {
                Main.getMain().getGame().getTimer().update(60);
                view.getTimeDiscount().setChecked(false);
            }
            if (view.getLevelIncrement().isChecked()) {
                Main.getMain().getGame().getHero().addLevel(view.getGameController());
                view.getLevelIncrement().setChecked(false);
            }
        }
    }
}
