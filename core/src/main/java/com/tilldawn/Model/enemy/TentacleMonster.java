package com.tilldawn.Model.enemy;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.tilldawn.Main;
import com.tilldawn.Model.AnimatedSprite;
import com.tilldawn.Model.Countdown;
import com.tilldawn.Model.GameAssetManager;

public class TentacleMonster extends Enemy {

    public TentacleMonster(float x, float y, float pos) {
        this.hp = 25;
        this.x = x;
        this.y = y;
    }

    @Override
    protected void init() {
        super.init();
        if (sprite != null) return;
        sprite = GameAssetManager.getGameAssetManager().getTentacleSpawning();
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                if (!dead) {
                    sprite = GameAssetManager.getGameAssetManager().getTentacle();
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
        return (int) Math.ceil((countdown.getDuration() - countdown.getRemaining()) / 30);
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
            velocity = direction;
            flipped = direction.x < 0;
        }
    }

    public boolean isCollided(float posX, float posY) {
        init();
        return Math.abs(posX - sprite.getX()) < 28 &&
            Math.abs(posY - sprite.getY()) < 50;
    }
}
