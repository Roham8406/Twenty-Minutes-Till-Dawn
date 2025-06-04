package com.tilldawn.Model.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.tilldawn.Main;
import com.tilldawn.Model.AnimatedSprite;

import java.io.Serializable;

public abstract class Enemy implements Serializable {
    private static final long serialVersionUID = 1L;
    protected Integer hp;
    protected transient Texture texture;
    protected transient Sprite sprite;
    protected boolean flipped;
    protected float x;
    protected float y;
    protected boolean dead;
    protected Vector2 velocity = new Vector2(0, 0).nor();
    protected transient TextureRegion[][] textureFrames;
    protected transient Animation<TextureRegion> animationFrames;
    protected transient Texture seed = new Texture("T/T_LunaBlackHoleDamage_0.png");
    protected transient Texture death = new Texture("T/T_DeathFX.png");

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
        textureFrames = TextureRegion.split(death, 40, 40);
        animationFrames = new Animation<>(0.1f, textureFrames[0][0], textureFrames[0][1],
            textureFrames[0][2], textureFrames[0][3]);
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                textureFrames = TextureRegion.split(seed, 42, 42);
                animationFrames = new Animation<>(0.3f, textureFrames[0][0], textureFrames[0][0]);
                sprite = new AnimatedSprite(animationFrames);
            }
        }, 0.5f);
        sprite = new AnimatedSprite(animationFrames);
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

    public boolean isCollisioned(float posX, float posY) {
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
