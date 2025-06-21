package com.tilldawn.Model.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.tilldawn.Main;
import com.tilldawn.Model.AnimatedSprite;
import com.tilldawn.Model.Countdown;
import com.tilldawn.Model.GameAssetManager;
import com.tilldawn.View.GameView;

public class Elder extends Enemy {
    private Float lastDash;
    private boolean dashing;
    private static boolean spawn = false;

    public Elder(float x, float y, float pos) {
        this.hp = 400;
        this.x = x;
        this.y = y;
        init();
        lastDash = Main.getMain().getGame().getTimer().getRemaining();
    }

    @Override
    protected void init() {
        super.init();
        if (sprite != null) return;
        sprite = GameAssetManager.getGameAssetManager().getElder();
    }

    public static Integer spawnCount() {
        Countdown countdown = Main.getMain().getGame().getTimer();
        if (spawn) {
            spawn = false;
            return 1;
        }
        if (Main.getMain().getGame().getLastSpawn().get(2) != countdown.getDuration() ||
            countdown.getRemaining() * 2 > countdown.getDuration()) {
            return 0;
        }
        Main.getMain().getGame().getLastSpawn().set(2, countdown.getRemaining());
        return 1;
    }

    @Override
    public void attack() {
        if (!dead) {
            Vector2 direction = new Vector2(
                -Main.getMain().getGame().getHero().getPosX() + Gdx.graphics.getWidth() / 2f - x,
                -Main.getMain().getGame().getHero().getPosY() + Gdx.graphics.getHeight() / 2f - y
            ).nor();
            velocity = direction;
            x += direction.x * (dashing ? 6 : 2);
            y += direction.y * (dashing ? 6 : 2);
            flipped = direction.x < 0;
            if (lastDash - 5 > Main.getMain().getGame().getTimer().getRemaining()) {
                dashing = true;
                lastDash = Main.getMain().getGame().getTimer().getRemaining();
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        dashing = false;
                    }
                }, 1);
            }
        }
    }

    @Override
    public void die() {
        if (hp > 0) return;
        super.die();
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                ((GameView) Main.getMain().getScreen()).getController().getWorldController().endBossFight();
            }
        }, 1);
    }

    public boolean isCollided(float posX, float posY) {
        init();
        return Math.abs(posX - sprite.getX()) < 80 &&
            Math.abs(posY - sprite.getY()) < 70;
    }

    public static void setSpawn() {
        spawn = true;
    }

    @Override
    public float getWidth() {
        return super.getWidth() * 1.7f;
    }

    @Override
    public float getHeight() {
        return super.getHeight() * 1.7f;
    }
}
