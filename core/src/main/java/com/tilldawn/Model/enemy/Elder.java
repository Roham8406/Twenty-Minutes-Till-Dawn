package com.tilldawn.Model.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.tilldawn.Main;
import com.tilldawn.Model.AnimatedSprite;
import com.tilldawn.Model.Countdown;
import com.tilldawn.Model.EnemyBullet;

public class Elder extends Enemy{
    private Float lastDash;
    private boolean dashing;

    public Elder(float x, float y, float pos) {
        this.hp = 400;
        this.x = x;
        this.y = y;
        texture = new Texture("T/T_ShubNiggurath.png");
        textureFrames = TextureRegion.split(texture, 96, 96);
        animationFrames = new Animation<>(0.3f, textureFrames[0][0], textureFrames[0][1],
            textureFrames[0][2]);
        sprite = new AnimatedSprite(animationFrames);
        lastDash = Main.getMain().getGame().getTimer().getRemaining();
    }
    public static Integer spawnCount() {
        Countdown countdown = Main.getMain().getGame().getTimer();
        if (Main.getMain().getGame().getLastSpawn().get(0) != countdown.getDuration()) {
            return 0;
        }
        Main.getMain().getGame().getLastSpawn().set(0, countdown.getRemaining() - 2);
        return 1;
    }

    @Override
    public void attack() {
        if (!dead) {
            Vector2 direction = new Vector2(
                -Main.getMain().getGame().getHero().getPosX() + Gdx.graphics.getWidth() / 2f - x,
                -Main.getMain().getGame().getHero().getPosY() + Gdx.graphics.getHeight() / 2f - y
            ).nor();
            x += direction.x * (dashing ? 6 : 2);
            y += direction.y * (dashing ? 6 : 2);
            flipped = direction.x < 0;
            if (lastDash - 5 > Main.getMain().getGame().getTimer().getRemaining()) {
                dashing = true;
                lastDash = Main.getMain().getGame().getTimer().getRemaining();
                Timer.schedule(new Timer.Task(){
                    @Override
                    public void run() {
                        dashing = false;
                    }
                }, 1);
            }
        }
    }

    @Override
    public void die() {
        if (hp > 0) return;
        super.die();
    }

    public boolean isCollisioned(float posX, float posY) {
        return Math.abs(posX - sprite.getX()) < 80 &&
            Math.abs(posY - sprite.getY()) < 70;
    }

    @Override
    public float getWidth() {
        return super.getWidth() * 1.7f;
    }

    @Override
    public float getHeight() {
        return super.getHeight() * 1.7f;
    }
}
