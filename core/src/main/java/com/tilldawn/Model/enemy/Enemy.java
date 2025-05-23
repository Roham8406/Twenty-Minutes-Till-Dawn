package com.tilldawn.Model.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.tilldawn.Model.AnimatedSprite;

public abstract class Enemy {
    protected Integer hp;
    protected Texture texture;
    protected Sprite sprite;
    protected float x;
    protected float y;
    protected float lastSpawn;
    protected boolean dead;
    protected TextureRegion[][] textureFrames;
    protected Animation<TextureRegion> animationFrames;
    protected Texture seed = new Texture("T/T_LunaBlackHoleDamage_0.png");

    public void removeHp(int hp){
        this.hp -= hp;
        if (this.hp <= 0) {
            dead = true;
            textureFrames = TextureRegion.split(seed, 42, 42);
            animationFrames = new Animation<>(0.3f, textureFrames[0][0], textureFrames[0][0]);
            sprite = new AnimatedSprite(animationFrames);

        }
    }

    public boolean isDead() {
        return dead;
    }

    public Sprite getSprite(float offsetX, float offsetY) {
        sprite.setX(x + offsetX);
        sprite.setY(y + offsetY);
        return sprite;
    }

    public abstract void attack();

    public boolean isCollisioned(float posX, float posY) {
        return Math.abs(posX + this.x - Gdx.graphics.getWidth()/2f) < 28 &&
            Math.abs(posY + this.y - Gdx.graphics.getHeight()/2f) < 50;
    }
}
