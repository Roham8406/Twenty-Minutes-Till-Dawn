package com.tilldawn.Model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.tilldawn.Main;

import java.io.Serializable;

public class EnemyBullet implements Serializable {
    private static final long serialVersionUID = 1L;
    private transient Texture texture = new Texture(GameAssetManager.getGameAssetManager().getBullet());
    private transient Sprite sprite;
    private int damage = 1;
    private int projectTile = 1;
    private float x;
    private float y;
    private float posX;
    private float posY;
    private int range = 120;

    public EnemyBullet(float x, float y) {
        this.x = x;
        this.y = y;
        this.posX = x;
        this.posY = y;
        init();
    }

    private void init() {
        if (sprite != null) return;
        sprite = new Sprite(texture);
        sprite.setSize(20, 20);
        updatePos();
    }

    public void updatePos() {
        init();
        sprite.setX(posX + Main.getMain().getGame().getHero().getPosX());
        sprite.setY(posY + Main.getMain().getGame().getHero().getPosY());
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

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getProjectTile() {
        return projectTile;
    }

    public boolean isCollisioned(float posX, float posY) {
        init();
        return Math.abs(posX - sprite.getX()) < 80 &&
            Math.abs(posY - sprite.getY()) < 70;
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
