package com.tilldawn.Model.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.tilldawn.Main;
import com.tilldawn.Model.GameAssetManager;

import java.io.Serializable;

public abstract class Enemy implements Serializable {
    private static final long serialVersionUID = 1L;
    protected Integer hp;
    protected transient Sprite sprite;
    protected boolean flipped;
    protected float x;
    protected float y;
    protected boolean dead;
    protected Vector2 velocity = new Vector2(0, 0).nor();

    public void removeHp(int hp) {
        this.hp -= hp;
        if (this.hp <= 0) {
            die();
        }
    }


    public Vector2 getVelocity() {
        return velocity;
    }

    public void die() {
        dead = true;
        Main.getMain().getGame().getHero().incrementKills();
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                sprite = GameAssetManager.getGameAssetManager().getDeadEnemy();
            }
        }, 0.5f);
        sprite = GameAssetManager.getGameAssetManager().getDeath();
    }

    public boolean isDead() {
        return dead;
    }

    public Sprite getSprite(float offsetX, float offsetY) {
        init();
        sprite.setX(x + offsetX);
        sprite.setY(y + offsetY);
        if (flipped) sprite.flip(true, false);
        return sprite;
    }

    public abstract void attack();

    public boolean isCollided(float posX, float posY) {
        init();
        return Math.abs(posX + this.x - Gdx.graphics.getWidth() / 2f) < 28 &&
            Math.abs(posY + this.y - Gdx.graphics.getHeight() / 2f) < 50;
    }

    public float getWidth() {
        return 1;
    }

    public float getHeight() {
        return 1;
    }

    public void goBack(Vector2 direction) {
        x -= direction.x * 20;
        y -= direction.y * 20;
    }

    protected void init() {

    }
}
