package com.tilldawn.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.tilldawn.Main;

public class Bullet {
    private Texture texture = new Texture(GameAssetManager.getGameAssetManager().getBullet());
    private Sprite sprite = new Sprite(texture);
    private int damage;
    private int projectTile;
    private float x;
    private float y;
    private float posX;
    private float posY;
    private int range = 120;

    public Bullet(float x, float y, int projectTile, int damage){
        sprite.setSize(20 , 20);
        this.x = x;
        this.posX = Main.getMain().getGame().getHero().getPosX();
        this.y = y;
        this.posY = Main.getMain().getGame().getHero().getPosY();
        this.damage = damage;
        this.projectTile = projectTile;
        updatePos();
    }

    public void updatePos() {
        sprite.setX(-posX + Main.getMain().getGame().getHero().getPosX() + (float) Gdx.graphics.getWidth() / 2);
        sprite.setY(-posY + Main.getMain().getGame().getHero().getPosY() + (float) Gdx.graphics.getHeight() / 2);
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
