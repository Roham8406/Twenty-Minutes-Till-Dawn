package com.tilldawn.Control;

import com.tilldawn.Main;
import com.tilldawn.Model.GameAssetManager;
import com.tilldawn.Model.GameCharacter;
import com.tilldawn.Model.MainGame;
import com.tilldawn.Model.WeaponType;
import com.tilldawn.View.GameView;
import com.tilldawn.View.PreGameMenuView;

public class PreGameMenuController extends MenuController {
    private PreGameMenuView view;


    public void setView(PreGameMenuView view) {
        this.view = view;
    }

    public void handlePreGameMenuButtons() {
        addClickSoundToButtons(view.getStage().getRoot());
        if (view != null) {
            if (view.getDasher().isChecked()) {
                setGameCharacter(GameCharacter.Dasher);
                view.getDasher().setChecked(false);
            }
            if (view.getDiamond().isChecked()) {
                setGameCharacter(GameCharacter.Diamond);
                view.getDiamond().setChecked(false);
            }
            if (view.getShana().isChecked()) {
                setGameCharacter(GameCharacter.Shana);
                view.getShana().setChecked(false);
            }
            if (view.getLilith().isChecked()) {
                setGameCharacter(GameCharacter.Lilith);
                view.getLilith().setChecked(false);
            }
            if (view.getScarlet().isChecked()) {
                setGameCharacter(GameCharacter.Scarlet);
                view.getScarlet().setChecked(false);
            }


            if (view.getRevolver().isChecked()) {
                setWeapon(WeaponType.Revolver);
                view.getRevolver().setChecked(false);
            }
            if (view.getShotgun().isChecked()) {
                setWeapon(WeaponType.Shotgun);
                view.getShotgun().setChecked(false);
            }
            if (view.getSmg().isChecked()) {
                setWeapon(WeaponType.Smg);
                view.getSmg().setChecked(false);
            }

            if (view.getTime2().isChecked()) {
                setTime(2);
                view.getTime2().setChecked(false);
            }
            if (view.getTime5().isChecked()) {
                setTime(5);
                view.getTime5().setChecked(false);
            }
            if (view.getTime10().isChecked()) {
                setTime(10);
                view.getTime10().setChecked(false);
            }
            if (view.getTime20().isChecked()) {
                setTime(20);
                view.getTime20().setChecked(false);
            }

            if (view.getStart().isChecked()) {
                Main.getMain().setGame(new MainGame());
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new GameView(new GameController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
        }
    }

    private void setGameCharacter(GameCharacter gameCharacter) {
        Main.getMain().setGameCharacter(gameCharacter);
        view.alert(Main.getLanguage().Character + gameCharacter.getName() + Main.getLanguage().Selected, 5);
    }

    private void setWeapon(WeaponType weapon) {
        Main.getMain().setWeaponType(weapon);
        view.alert(Main.getLanguage().Weapon + weapon.getName() + Main.getLanguage().Selected, 5);
    }

    private void setTime(Integer time) {
        Main.getMain().setTime(time);
        view.alert(Main.getLanguage().TimeAdjusted + time + " " + Main.getLanguage().Minutes + ".", 5);
    }


}
