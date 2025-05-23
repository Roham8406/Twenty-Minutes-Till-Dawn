package com.tilldawn.Model;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimatedSprite extends Sprite {
    private final Animation<TextureRegion> animation;
    private float stateTime = 0f;
    private boolean looping = true;

    public AnimatedSprite(Animation<TextureRegion> animation) {
        super(animation.getKeyFrame(0)); // Initialize with first frame
        this.animation = animation;
    }

    public void update(float delta) {
        stateTime += delta;
        this.setRegion(animation.getKeyFrame(stateTime, looping));
    }

    public void setLooping(boolean looping) {
        this.looping = looping;
    }

    public boolean isAnimationFinished() {
        return animation.isAnimationFinished(stateTime);
    }
}
