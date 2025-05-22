package com.tilldawn.Control;

import com.tilldawn.Main;
import com.tilldawn.Model.Player;
import com.tilldawn.Model.Weapon;
import com.tilldawn.View.GameView;

public class GameController {
    private GameView view;
    private PlayerController playerController;
    private WorldController worldController;
    private WeaponController weaponController;


    public void setView(GameView view) {
        this.view = view;
        playerController = new PlayerController(Main.getMain().getGame().getHero());
        worldController = new WorldController(playerController);
        weaponController = new WeaponController(Main.getMain().getGame().getWeapon());
    }

    public void updateGame() {
        if (view != null) {

            worldController.update();
            playerController.update();
            weaponController.update();
        }
    }

    public PlayerController getPlayerController() {
        return playerController;
    }

    public WeaponController getWeaponController() {
        return weaponController;
    }
}
