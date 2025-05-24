package com.tilldawn.Control;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.tilldawn.Main;
import com.tilldawn.Model.GameAssetManager;
import com.tilldawn.View.AbilityMenuView;
import com.tilldawn.View.ControlSettingsMenuView;
import com.tilldawn.View.MainMenuView;
import com.tilldawn.View.SettingsMenuView;

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
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(view.getGameController().getView());
            }
            if (view.getDamager().isChecked()) {
                Main.getMain().getGame().getWeapon().incrementDamage();
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(view.getGameController().getView());
            }
            if (view.getProcrease().isChecked()) {
                Main.getMain().getGame().getWeapon().incrementProjectile();
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(view.getGameController().getView());
            }
            if (view.getVitality().isChecked()) {
                Main.getMain().getGame().getHero().incrementMaxHp();
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(view.getGameController().getView());
            }
            if (view.getSpeedy().isChecked()) {
                Main.getMain().getGame().getHero().incrementSpeed();
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(view.getGameController().getView());
            }
        }
    }
}
