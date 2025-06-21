package com.tilldawn.Model.enemy;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.tilldawn.Main;
import com.tilldawn.Model.AnimatedSprite;
import com.tilldawn.Model.Countdown;
import com.tilldawn.Model.EnemyBullet;
import com.tilldawn.Model.GameAssetManager;

public class EyeBat extends Enemy {
    private Float lastShot;

    public EyeBat(float x, float y, float pos) {
        this.hp = 50;
        this.x = x;
        this.y = y;
        lastShot = Main.getMain().getGame().getTimer().getRemaining();

    }

    @Override
    protected void init() {
        super.init();
        if (sprite != null) return;
        sprite = GameAssetManager.getGameAssetManager().getEyeBat();
    }

    public static Integer spawnCount() {
        Countdown countdown = Main.getMain().getGame().getTimer();
        if (countdown.getRemaining() > countdown.getDuration() * 3f / 4f) return 0;
        if (Main.getMain().getGame().getLastSpawn().get(1) - 10 < countdown.getRemaining()) {
            return 0;
        }
        Main.getMain().getGame().getLastSpawn().set(1, countdown.getRemaining());
        return (int) Math.ceil((3 * countdown.getDuration() - 4 * countdown.getRemaining() + 30) / 30);
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
            if (lastShot - 3 > Main.getMain().getGame().getTimer().getRemaining()) {
                Main.getMain().getGame().getEnemyBullets().add(new EnemyBullet(x, y));
                lastShot = Main.getMain().getGame().getTimer().getRemaining();
            }
        }
    }

    public boolean isCollided(float posX, float posY) {
        init();
        return Math.abs(posX - sprite.getX()) < 80 &&
            Math.abs(posY - sprite.getY()) < 70;
    }
}
