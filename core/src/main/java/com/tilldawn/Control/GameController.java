package com.tilldawn.Control;

import com.tilldawn.Main;
import com.tilldawn.View.GameView;

public class GameController {
    private GameView view;
    private PlayerController playerController;
    private WorldController worldController;
    private WeaponController weaponController;


    public void setView(GameView view) {
        this.view = view;
        playerController = new PlayerController(Main.getMain().getGame().getHero(), this);
        worldController = new WorldController(playerController, this);
        weaponController = new WeaponController(worldController, Main.getMain().getGame().getWeapon());
    }

    public void updateGame(float delta) {
        if (view != null) {
            worldController.update(delta);
            playerController.update();
            weaponController.update(delta);
        }
    }

    public PlayerController getPlayerController() {
        return playerController;
    }

    public WeaponController getWeaponController() {
        return weaponController;
    }

    public WorldController getWorldController() {
        return worldController;
    }

    public GameView getView() {
        return view;
    }
}
