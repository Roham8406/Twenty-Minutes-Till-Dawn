package com.tilldawn.Model.enemy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class Enemy {
    protected Integer hp;
    protected Texture texture;
    protected Sprite sprite;
    protected float x;
    protected float y;
    protected float lastSpawn;
    protected boolean isDead;
    protected TextureRegion[][] textureFrames;
    protected Animation<TextureRegion> animationFrames;

    public boolean removeHp(int hp){
        this.hp -= hp;
        return hp <= 0;
    }

    public Sprite getSprite(float offsetX, float offsetY) {
        sprite.setX(x + offsetX);
        sprite.setY(y + offsetY);
        return sprite;
    }

    public abstract void attack();
}
