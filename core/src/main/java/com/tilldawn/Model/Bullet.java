package com.tilldawn.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.tilldawn.Main;

import java.io.Serializable;

public class Bullet implements Serializable {
    private static final long serialVersionUID = 1L;

    private final transient Texture texture = GameAssetManager.getGameAssetManager().getBullet();
    private transient Sprite sprite;
    private final int damage;
    private final float projectTile;
    private final Vector2 velocity;
    private float posX;
    private float posY;
    private int range = 120;

    public Bullet(float x, float y, float projectTile, int damage) {
        this.posX = Main.getMain().getGame().getHero().getPosX();
        this.posY = Main.getMain().getGame().getHero().getPosY();
        this.damage = damage;
        this.projectTile = projectTile;
        velocity = new Vector2(
            Gdx.graphics.getWidth() / 2f - x,
            -Gdx.graphics.getHeight() / 2f + y
        ).nor();
        velocity.rotateDeg(projectTile * 3);

        updatePos();
    }

    private void init() {
        if (sprite != null) {
            return;
        }
        sprite = new Sprite(texture);
        sprite.setSize(20, 20);
    }

    public void updatePos() {
        init();
        sprite.setX(-posX + Main.getMain().getGame().getHero().getPosX() + (float) Gdx.graphics.getWidth() / 2);
        sprite.setY(-posY + Main.getMain().getGame().getHero().getPosY() + (float) Gdx.graphics.getHeight() / 2);
    }

    public Texture getTexture() {
        init();
        return texture;
    }

    public Sprite getSprite() {
        init();
        return sprite;
    }

    public int getDamage() {
        return damage;
    }

    public void decrementTile() {
        range--;
    }

    public boolean isRangeEnded() {
        return range == 0;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }

    public float getPosX() {
        return posX;
    }

    public float getPosY() {
        return posY;
    }
}
