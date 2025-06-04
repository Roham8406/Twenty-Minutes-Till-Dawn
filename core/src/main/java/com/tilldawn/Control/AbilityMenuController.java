package com.tilldawn.Control;

import com.tilldawn.Main;
import com.tilldawn.View.AbilityMenuView;

public class AbilityMenuController extends MenuController {
    private AbilityMenuView view;

    public void setView(AbilityMenuView view) {
        this.view = view;
    }

    public void handleMainMenuButtons() {
        addClickSoundToButtons(view.getStage().getRoot());
        if (view != null) {
            if (view.getAmocrease().isChecked()) {
                Main.getMain().getGame().getWeapon().incrementAmmo();
                Main.getMain().getGame().incrementAmocrease();
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(view.getGameController().getView());
            }
            if (view.getDamager().isChecked()) {
                Main.getMain().getGame().getWeapon().incrementDamage();
                Main.getMain().getGame().incrementDamager();
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(view.getGameController().getView());
            }
            if (view.getProcrease().isChecked()) {
                Main.getMain().getGame().getWeapon().incrementProjectile();
                Main.getMain().getGame().incrementProcrease();
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(view.getGameController().getView());
            }
            if (view.getVitality().isChecked()) {
                Main.getMain().getGame().getHero().incrementMaxHp();
                Main.getMain().getGame().incrementVitality();
                view.getGameController().getWorldController().addHeart();
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(view.getGameController().getView());
            }
            if (view.getSpeedy().isChecked()) {
                Main.getMain().getGame().getHero().incrementSpeed();
                Main.getMain().getGame().incrementSpeedy();
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(view.getGameController().getView());
            }
        }
    }
}
