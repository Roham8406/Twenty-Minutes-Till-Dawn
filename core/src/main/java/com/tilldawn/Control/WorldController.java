package com.tilldawn.Control;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.tilldawn.Main;

import java.awt.*;

public class WorldController {
    private PlayerController playerController;
    private Texture backgroundTexture;
    private Label timer;
    private BitmapFont font;
    private float backgroundX = 0;
    private float backgroundY = 0;
    GameController gameController;;

    public WorldController(PlayerController playerController, GameController gameController) {
        this.backgroundTexture = new Texture("background.png");
        this.playerController = playerController;
        this.gameController = gameController;
        this.timer = new Label("");
        this.timer.setBounds(0,0,50,50);
        this.font = new BitmapFont();
    }

    public void update() {
        backgroundX = playerController.getPlayer().getPosX();
        backgroundY = playerController.getPlayer().getPosY();
        timer.setText(Main.getMain().getGame().getTimer().toString());
        Main.getBatch().draw(backgroundTexture, backgroundX, backgroundY);
        font.draw(Main.getBatch(),  Main.getMain().getGame().getTimer().toString(), backgroundX,backgroundY);
//        Main.getBatch().draw(timer);
    }

}
