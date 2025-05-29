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

public class TentacleMonster extends Enemy{

    public TentacleMonster(float x, float y, float pos) {
        this.hp = 25;
        this.x = x;
        this.y = y;
        texture = new Texture("T/T_TentacleEnemy.png");
        textureFrames = TextureRegion.split(texture, 64, 64);
        animationFrames = new Animation<>(0.3f, textureFrames[0][0], textureFrames[0][1],
            textureFrames[0][2]);
        sprite = new AnimatedSprite(animationFrames);
        Timer.schedule(new Timer.Task(){
            @Override
            public void run() {
                if (!dead) {
                    animationFrames = new Animation<>(0.3f, textureFrames[2][0], textureFrames[2][1],
                        textureFrames[2][2], textureFrames[2][3]);
                    sprite = new AnimatedSprite(animationFrames);
                }
            }
        }, 1);
    }
    public static Integer spawnCount() {
        Countdown countdown = Main.getMain().getGame().getTimer();
        if (Main.getMain().getGame().getLastSpawn().get(0) - 3 < countdown.getRemaining()) {
            return 0;
        }
        Main.getMain().getGame().getLastSpawn().set(0, countdown.getRemaining());
        return (int)Math.ceil((countdown.getDuration() - countdown.getRemaining())/30);
    }

    @Override
    public void attack() {
        if (!dead) {
            Vector2 direction = new Vector2(
                -Main.getMain().getGame().getHero().getPosX() + Gdx.graphics.getWidth() / 2f - x,
                -Main.getMain().getGame().getHero().getPosY() + Gdx.graphics.getHeight() / 2f - y
            ).nor();
            x += direction.x * 1;
            y += direction.y * 1;
            flipped = direction.x < 0;
        }
    }

    public boolean isCollisioned(float posX, float posY) {
        return Math.abs(posX - sprite.getX()) < 28 &&
            Math.abs(posY - sprite.getY()) < 50;
    }
}
