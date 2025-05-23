package com.tilldawn.Control;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.Timer;
import com.tilldawn.Main;
import com.tilldawn.Model.AnimatedSprite;
import com.tilldawn.Model.Sfx;
import com.tilldawn.Model.enemy.Tree;

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

    public void update(float delta) {
        backgroundX = playerController.getPlayer().getPosX();
        backgroundY = playerController.getPlayer().getPosY();
        timer.setText(Main.getMain().getGame().getTimer().toString());
        Main.getBatch().draw(backgroundTexture, backgroundX, backgroundY);
        for (Tree tree : Main.getMain().getGame().getTrees()) {
//            Main.getBatch().draw(tree.getTexture(), tree.getX() + backgroundX, tree.getY() + backgroundY);
            tree.getSprite(backgroundX, backgroundY).draw(Main.getBatch());
            ((AnimatedSprite)tree.getSprite(backgroundX, backgroundY)).update(delta);
            if (!Main.getMain().getGame().getHero().isInvincible()) {
                if (tree.isCollisioned(backgroundX, backgroundY)) {
                    Main.getMain().getGame().getHero().removeHp(1);
                    Main.getMain().getGame().getHero().setInvincible(true);
                    if (Main.getMain().isSfx()) Sfx.Hurt.play();
                    Timer.schedule(new Timer.Task(){
                        @Override
                        public void run() {
                            Main.getMain().getGame().getHero().setInvincible(false);
                        }
                    }, 3);
                }
            }
        }
        font.draw(Main.getBatch(),  Main.getMain().getGame().getTimer().toString() + " HP: " +
            Main.getMain().getGame().getHero().getPlayerHealth(), backgroundX,backgroundY);
//        Main.getBatch().draw(timer);
    }

}
