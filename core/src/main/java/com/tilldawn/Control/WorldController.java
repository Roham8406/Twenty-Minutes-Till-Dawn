package com.tilldawn.Control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.Timer;
import com.tilldawn.Main;
import com.tilldawn.Model.AnimatedSprite;
import com.tilldawn.Model.Sfx;
import com.tilldawn.Model.enemy.Enemy;
import com.tilldawn.Model.enemy.EyeBat;
import com.tilldawn.Model.enemy.TentacleMonster;
import com.tilldawn.Model.enemy.Tree;

import java.awt.*;
import java.util.Random;

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
            tree.getSprite(backgroundX, backgroundY).draw(Main.getBatch());
            ((AnimatedSprite)tree.getSprite(backgroundX, backgroundY)).update(delta);
            if (!Main.getMain().getGame().getHero().isInvincible()) {
                if (tree.isCollisioned(backgroundX, backgroundY)) {
                    hurt();
                }
            }
        }
        font.draw(Main.getBatch(),  Main.getMain().getGame().getTimer().toString() + " HP: " +
            Main.getMain().getGame().getHero().getPlayerHealth(), backgroundX,backgroundY);
//        Main.getBatch().draw(timer);
        spawnEnemies(delta);
        for (Enemy enemy : Main.getMain().getGame().getEnemies().toArray(
            new Enemy[Main.getMain().getGame().getEnemies().size()]
        )) {
            enemy.getSprite(backgroundX, backgroundY).draw(Main.getBatch());
            ((AnimatedSprite) enemy.getSprite(backgroundX, backgroundY)).update(delta);
            if (!Main.getMain().getGame().getHero().isInvincible()) {
                if (enemy.isCollisioned(Gdx.graphics.getWidth()/2f, Gdx.graphics.getHeight()/2f)) {
                    if (enemy.isDead()) {
                        playerController.getPlayer().incrementXp();
                        Main.getMain().getGame().getEnemies().remove(enemy);
                    } else {
                        hurt();
                    }
                }
            }
        }
        enemiesAttack(delta);
    }

    private void spawnEnemies(float delta) {
        Random random = new Random();
        int tentacleCount = 0;
//        int tentacleCount = TentacleMonster.spawnCount();
        for (int i = 0; i < tentacleCount; i++) {
            float x,y;
            switch (random.nextInt(4)) {
                case 0: {
                    x = -playerController.getPlayer().getPosX() + random.nextFloat(Gdx.graphics.getWidth());
                    y = -playerController.getPlayer().getPosY() + Gdx.graphics.getHeight();
                } break;
                case 1: {
                    x = -playerController.getPlayer().getPosX() + random.nextFloat(Gdx.graphics.getWidth());
                    y = -playerController.getPlayer().getPosY() - 0;
                } break;
                case 2: {
                    x = -playerController.getPlayer().getPosX() + Gdx.graphics.getWidth();
                    y = -playerController.getPlayer().getPosY() + random.nextFloat(Gdx.graphics.getHeight());
                } break;
                default: {
                    x = -playerController.getPlayer().getPosX() - 0;
                    y = -playerController.getPlayer().getPosY() + random.nextFloat(Gdx.graphics.getHeight());
                }
            }
            TentacleMonster tentacleMonster = new TentacleMonster(x,y,delta);
            Main.getMain().getGame().getEnemies().add(tentacleMonster);
        }

        int eyeBatCount = EyeBat.spawnCount();
        if (Main.getMain().getGame().getEnemies().size() > 0) {eyeBatCount = 0;}
        for (int i = 0; i < eyeBatCount; i++) {
            float x,y;
            switch (random.nextInt(4)) {
                case 0: {
                    x = -playerController.getPlayer().getPosX() + random.nextFloat(Gdx.graphics.getWidth());
                    y = -playerController.getPlayer().getPosY() + Gdx.graphics.getHeight();
                } break;
                case 1: {
                    x = -playerController.getPlayer().getPosX() + random.nextFloat(Gdx.graphics.getWidth());
                    y = -playerController.getPlayer().getPosY() - 0;
                } break;
                case 2: {
                    x = -playerController.getPlayer().getPosX() + Gdx.graphics.getWidth();
                    y = -playerController.getPlayer().getPosY() + random.nextFloat(Gdx.graphics.getHeight());
                } break;
                default: {
                    x = -playerController.getPlayer().getPosX() - 0;
                    y = -playerController.getPlayer().getPosY() + random.nextFloat(Gdx.graphics.getHeight());
                }
            }
            EyeBat eyeBat = new EyeBat(x,y,delta);
            Main.getMain().getGame().getEnemies().add(eyeBat);
        }
    }

    private void enemiesAttack(float delta) {
        for (Enemy enemy : Main.getMain().getGame().getEnemies()) {
            enemy.attack();
        }
    }

    private void hurt() {
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
