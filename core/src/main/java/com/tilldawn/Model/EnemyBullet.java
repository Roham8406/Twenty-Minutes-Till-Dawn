package com.tilldawn.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.tilldawn.Main;

public class EnemyBullet {
    private Texture texture = new Texture(GameAssetManager.getGameAssetManager().getBullet());
    private Sprite sprite = new Sprite(texture);
    private int damage = 1;
    private int projectTile = 1;
    private float x;
    private float y;
    private int range = 120;

    public EnemyBullet(float x, float y){
        sprite.setSize(20 , 20);
        this.x = x;
        this.y = y;
        sprite.setX(x + Main.getMain().getGame().getHero().getPosX());
        sprite.setY(y + Main.getMain().getGame().getHero().getPosY());
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
        return Math.abs(posX - sprite.getX()) < 80 &&
            Math.abs(posY - sprite.getY()) < 70;
    }
}
