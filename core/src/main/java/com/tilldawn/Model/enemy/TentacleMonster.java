package com.tilldawn.Model.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
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
        animationFrames = new Animation<>(0.3f, textureFrames[2][0], textureFrames[2][1],
            textureFrames[2][2], textureFrames[2][3]);
        sprite = new AnimatedSprite(animationFrames);
        ((AnimatedSprite)sprite).update(pos);
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
//        Vector2 direction = new Vector2(
//            Gdx.graphics.getWidth()/2f - x,
//            Gdx.graphics.getHeight()/2f - y
//        ).nor();
        Vector2 direction = new Vector2(
            -Main.getMain().getGame().getHero().getPosX() + Gdx.graphics.getWidth()/2f - x,
            -Main.getMain().getGame().getHero().getPosY() + Gdx.graphics.getHeight()/2f - y
        ).nor();
        x += direction.x * 1;
        y += direction.y * 1;
    }
}
