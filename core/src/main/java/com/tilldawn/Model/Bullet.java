package com.tilldawn.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Bullet {
    private Texture texture = new Texture(GameAssetManager.getGameAssetManager().getBullet());
    private Sprite sprite = new Sprite(texture);
    private int damage;
    private int projectTile;
    private int x;
    private int y;
    private int range = 120;

    public Bullet(int x, int y, int projectTile, int damage){
        sprite.setSize(20 , 20);
        this.x = x;
        this.y = y;
        this.damage = damage;
        this.projectTile = projectTile;
        sprite.setX((float) Gdx.graphics.getWidth() / 2);
        sprite.setY((float) Gdx.graphics.getHeight() / 2);
    }

    public Texture getTexture() {
        return texture;
    }

    public Sprite getSprite() {
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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getProjectTile() {
        return projectTile;
    }
}
