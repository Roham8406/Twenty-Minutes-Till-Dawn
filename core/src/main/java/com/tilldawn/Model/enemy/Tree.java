package com.tilldawn.Model.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.tilldawn.Model.AnimatedSprite;

import java.io.Serializable;
import java.util.Random;


public class Tree implements Serializable {
    private static final long serialVersionUID = 1L;
    private transient Texture texture;
    private transient Sprite sprite;
    private final int x;
    private final int y;
    private transient TextureRegion[][] textureFrames;

    public Tree(int x, int y, float pos) {
        this.x = x;
        this.y = y;
        init();
    }

    private void init() {
        if (texture != null) return;
        texture = new Texture("T/T_TreeMonster.png");
        textureFrames = TextureRegion.split(texture, 96, 96);
        Animation<TextureRegion> animationFrames = new Animation<>(0.3f, textureFrames[0][0], textureFrames[0][1],
            textureFrames[0][2], textureFrames[0][1]);
        sprite = new AnimatedSprite(animationFrames);
        ((AnimatedSprite) sprite).update(new Random().nextFloat());
    }

    public TextureRegion getTexture() {
        init();
        return textureFrames[0][0];
    }

    public Sprite getSprite(float offsetX, float offsetY) {
        init();
        sprite.setX(x + offsetX);
        sprite.setY(y + offsetY);
        return sprite;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isCollisioned(float posX, float posY) {
        return Math.abs(posX + this.x - Gdx.graphics.getWidth() / 2f) < 28 &&
            Math.abs(posY + this.y - Gdx.graphics.getHeight() / 2f) < 50;
    }
}
