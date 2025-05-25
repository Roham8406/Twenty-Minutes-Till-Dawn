package com.tilldawn.Control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Timer;
import com.tilldawn.Main;
import com.tilldawn.Model.AnimatedSprite;
import com.tilldawn.Model.Sfx;
import com.tilldawn.Model.enemy.Enemy;
import com.tilldawn.Model.enemy.EyeBat;
import com.tilldawn.Model.enemy.TentacleMonster;
import com.tilldawn.Model.enemy.Tree;

import java.util.ArrayList;
import java.util.Random;

public class WorldController {
    private PlayerController playerController;
    private Texture heartTexture;
    private Texture backgroundTexture;
    private TextureRegion[][] heartFrames;
    private Animation<TextureRegion> heartAnimation;
    private Animation<TextureRegion> deadHeart;
    private Sprite timer;
    private BitmapFont font;
    private float backgroundX = 0;
    private float backgroundY = 0;
    private ArrayList<AnimatedSprite> heartSprites = new ArrayList<>();
    GameController gameController;

    public WorldController(PlayerController playerController, GameController gameController) {
        this.backgroundTexture = new Texture("background.png");
        this.playerController = playerController;
        this.gameController = gameController;
        Texture spriteTexture = new Texture(Gdx.files.internal("T/T_TentacleAttackIndicator_0.png"));
        this.timer = new Sprite(spriteTexture);
//        this.timer.setCenterX(Gdx.graphics.getWidth()/2f);
//        this.timer.setCenterY(Gdx.graphics.getHeight() - 20);
        this.timer.setBounds(440,Gdx.graphics.getHeight()-62,Gdx.graphics.getWidth()-880,60);
        this.font = new BitmapFont();
        this.heartTexture = new Texture("T/T_HeartAnimation.png");
        this.heartFrames = TextureRegion.split(heartTexture, 32, 32);
        this.heartAnimation = new Animation<>(0.2f, heartFrames[0][0], heartFrames[0][1], heartFrames[0][2]);
        this.deadHeart = new Animation<>(0.2f, heartFrames[0][3], heartFrames[0][3]);
        for (int i = 0; i < Main.getMain().getGame().getHero().getMaxHp(); i++) {
            heartSprites.add(new AnimatedSprite(heartAnimation));
            heartSprites.get(i).setPosition(470 + 30*i, timer.getY() + 15);
        }
    }

    public void update(float delta) {
        backgroundX = playerController.getPlayer().getPosX();
        backgroundY = playerController.getPlayer().getPosY();
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
        timer.draw(Main.getBatch());
        font.draw(Main.getBatch(), Main.getMain().getGame().getTimer().toString(), timer.getX() + timer.getWidth()/2f - 20
            , timer.getY() + timer.getHeight()/2f + 10);
        drawHearts(delta);
        spawnEnemies(delta);
        Enemy nearest = null;
        double distance = Integer.MAX_VALUE;
        int x = 0,y = 0;
        for (Enemy enemy : Main.getMain().getGame().getEnemies().toArray(
            new Enemy[Main.getMain().getGame().getEnemies().size()]
        )) {
            Sprite sprite = enemy.getSprite(backgroundX, backgroundY);
            sprite.draw(Main.getBatch());
            ((AnimatedSprite) sprite).update(delta);
            if (!enemy.isDead()) {
                double dist = Math.sqrt(Math.pow(sprite.getY()-Gdx.graphics.getHeight()/2f, 2)
                    + Math.pow(sprite.getX()-Gdx.graphics.getWidth()/2f, 2));
                if (dist <= distance) {
                    nearest = enemy;
                    distance = dist;
                    x = (int) sprite.getX();
                    y = (int) sprite.getY();
                }
            }
            if (enemy.isCollisioned(Gdx.graphics.getWidth()/2f, Gdx.graphics.getHeight()/2f)) {
                if (enemy.isDead()) {
                    playerController.getPlayer().incrementXp(gameController);
                    Main.getMain().getGame().getEnemies().remove(enemy);
                } else {
                    if (!Main.getMain().getGame().getHero().isInvincible()) {
                        hurt();
                    } else {
                        enemy.die();
                    }
                }
            }
        }
        if (Main.getMain().getGame().isAutoAim()) {
            if (nearest != null) {
                Gdx.input.setCursorPosition(x + 32, Gdx.graphics.getHeight()-y - 32);
            }
        }
        enemiesAttack(delta);
    }

    private void spawnEnemies(float delta) {
        Random random = new Random();
        int tentacleCount = TentacleMonster.spawnCount();
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
        killHeart();
        Timer.schedule(new Timer.Task(){
            @Override
            public void run() {
                Main.getMain().getGame().getHero().setInvincible(false);
            }
        }, 3);
    }

    private void drawHearts(float delta) {
        for (AnimatedSprite heartSprite : heartSprites) {
            heartSprite.update(delta);
            heartSprite.draw(Main.getBatch());
        }
    }

    public void killHeart() {
        heartSprites.get(Main.getMain().getGame().getHero().getPlayerHealth()).edit(deadHeart);
    }

    public void reviveHeart() {
        heartSprites.get(Main.getMain().getGame().getHero().getPlayerHealth() - 1).edit(heartAnimation);
    }
}
