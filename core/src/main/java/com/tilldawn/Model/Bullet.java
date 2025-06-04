package com.tilldawn.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.tilldawn.Main;

import java.io.Serializable;

public class Bullet implements Serializable {
    private static final long serialVersionUID = 1L;

    private transient Texture texture = new Texture(GameAssetManager.getGameAssetManager().getBullet());
    private transient Sprite sprite;
    private int damage;
    private int projectTile;
    private float x;
    private float y;
    private float posX;
    private float posY;
    private int range = 120;

    public Bullet(float x, float y, int projectTile, int damage) {
        this.x = x;
        this.posX = Main.getMain().getGame().getHero().getPosX();
        this.y = y;
        this.posY = Main.getMain().getGame().getHero().getPosY();
        this.damage = damage;
        this.projectTile = projectTile;
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

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getProjectTile() {
        return projectTile;
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
