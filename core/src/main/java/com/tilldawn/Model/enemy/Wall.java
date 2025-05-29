package com.tilldawn.Model.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.tilldawn.Model.AnimatedSprite;


public class Wall {
    private Texture texture;
    private Sprite sprite;
    private float x;
    private float y;
    private float scaleX;
    private float scaleY;
    private TextureRegion[][] textureFrames;
    private Animation<TextureRegion> animationFrames;
    private boolean horizontal;
    private boolean nearOrigin;

    public Wall(int x, int y, float pos, boolean horizontal, boolean nearOrigin) {
        this.nearOrigin = nearOrigin;
        setX(x);
        setY(y);
        this.horizontal = horizontal;
        texture = new Texture("T/T_ElectricWall.png");
        textureFrames = TextureRegion.split(texture, 64, 32);
        animationFrames = new Animation<>(0.2f, textureFrames[0][0], textureFrames[0][1],
            textureFrames[0][2], textureFrames[0][3], textureFrames[0][4], textureFrames[0][5]);
        sprite = new AnimatedSprite(animationFrames);
        ((AnimatedSprite)sprite).update(pos);
        scaleX = horizontal ? 0.5f : 59;
        scaleY = horizontal ? 84 : 1;
    }

    public void setScaleX(float scaleX) {
        this.scaleX = scaleX;
    }

    public float getScaleX() {
        return scaleX;
    }

    public void setScaleY(float scaleY) {
        this.scaleY = scaleY;
    }

    public float getScaleY() {
        return scaleY;
    }

    public TextureRegion getTexture() {
        return textureFrames[0][0];
    }

    public Sprite getSprite(float offsetX, float offsetY) {
        sprite.setX(x + offsetX);
        sprite.setY(y + offsetY);
        if (horizontal) {
            sprite.rotate90(true);
        }
        sprite.setScale(scaleX, scaleY);
        return sprite;
    }

    public void setX(float x) {
        this.x = x - 32;
    }

    public void setY(float y) {
        this.y = y - 16;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Vector2 getGoBack() {
        if (nearOrigin) {
            if (!horizontal) {
                return new Vector2(0, 10);
            } else {
                return new Vector2(10, 0);
            }
        } else  {
            if (!horizontal) {
                return new Vector2(0, -10);
            } else {
                return new Vector2(-10, 0);
            }
        }
    }

    public boolean isCollisioned(float posX, float posY) {
        return Math.abs(posX + this.x - Gdx.graphics.getWidth()/2f) < 64 * scaleX &&
            Math.abs(posY + this.y - Gdx.graphics.getHeight()/2f) < 32 * scaleY;
    }

    public void setAbsY(float y) {
        this.y = y;
    }

    public void setAbsX(float x) {
        this.x = x;
    }
}
