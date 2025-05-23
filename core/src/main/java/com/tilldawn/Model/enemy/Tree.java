package com.tilldawn.Model.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.tilldawn.Model.AnimatedSprite;


public class Tree {
    private Texture texture;
    private Sprite sprite;
    private int x;
    private int y;
    private TextureRegion[][] textureFrames;
    private Animation<TextureRegion> animationFrames;

    public Tree(int x, int y, float pos){
        this.x = x;
        this.y = y;
        texture = new Texture("T/T_TreeMonster.png");
        textureFrames = TextureRegion.split(texture, 96, 96);
        animationFrames = new Animation<>(0.3f, textureFrames[0][0], textureFrames[0][1],
            textureFrames[0][2], textureFrames[0][1]);
        sprite = new AnimatedSprite(animationFrames);
        ((AnimatedSprite)sprite).update(pos);
    }

    public TextureRegion getTexture() {
        return textureFrames[0][0];
    }

    public Sprite getSprite(float offsetX, float offsetY) {
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
        return Math.abs(posX + this.x - Gdx.graphics.getWidth()/2) < 28 &&
            Math.abs(posY + this.y - Gdx.graphics.getHeight()/2) < 50;
    }
}
