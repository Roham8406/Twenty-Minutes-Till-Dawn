package com.tilldawn.Control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.tilldawn.Main;
import com.tilldawn.Model.*;
import com.tilldawn.Model.enemy.Enemy;

import java.util.ArrayList;

public class WeaponController {
    private final Weapon weapon;
    private final ArrayList<Bullet> bullets = new ArrayList<>();

    public WeaponController(Weapon weapon){
        this.weapon = weapon;
    }

    public boolean isReloading() {
        return weapon.isReloading();
    }

    public void update(float delta) {
        weapon.getSprite().draw(Main.getBatch());
        if (weapon.isReloading()) {
            ((AnimatedSprite)weapon.getSprite()).update(delta);
        }
        updateBullets();
    }

    public void handleWeaponRotation(int x, int y) {
        Sprite weaponSprite = weapon.getSprite();

        float weaponCenterX = (float) Gdx.graphics.getWidth() / 2;
        float weaponCenterY = (float) Gdx.graphics.getHeight() / 2;

        float angle = (float) Math.atan2(y - weaponCenterY, x - weaponCenterX);

        weaponSprite.setRotation((float) (3.14 - angle * MathUtils.radiansToDegrees));
    }

    public void handleWeaponShoot(int x, int y){
        if (weapon.canShoot()) {
            for (int i = 0; i < weapon.getProjectTile(); i++) {
                Timer.schedule(new Timer.Task(){
                    @Override
                    public void run() {
                        bullets.add(new Bullet(x, y, weapon.getProjectTile(), weapon.getDamage()));
                    }
                }, 0.2f * i);
            }
            weapon.setAmmo(weapon.getAmmo() - 1);
            if (Main.getMain().isSfx()) Sfx.Shot.play();
            if (Main.getMain().getGame().isAutoReload() && weapon.getAmmo() == 0) {
                weapon.reload();
            }
        }
    }

    public void updateBullets() {
        for(Bullet b : bullets.toArray(new Bullet[bullets.size()])) {
            b.getSprite().draw(Main.getBatch());
            Vector2 direction = new Vector2(
                Gdx.graphics.getWidth()/2f - b.getX(),
                Gdx.graphics.getHeight()/2f - b.getY()
            ).nor(); //todo not working properly when walking and shooting

            for (Enemy enemy : Main.getMain().getGame().getEnemies().toArray(new Enemy[Main.getMain().getGame().getEnemies().size()])) {
                if (enemy.isCollisioned(b.getSprite().getX(), b.getSprite().getY())) {
                    enemy.removeHp(b.getDamage());
                    Main.getMain().getGame().getHero().incrementKills();
                    bullets.remove(b);
                }
            }

            b.setPosX(b.getPosX() + direction.x * 5);
            b.setPosY(b.getPosY() - direction.y * 5);
            b.updatePos();
            b.decrementTile();
            if (b.isRangeEnded()) {
                bullets.remove(b);
            }
        }
        for(EnemyBullet b : Main.getMain().getGame().getBullets().toArray(
            new EnemyBullet[Main.getMain().getGame().getBullets().size()]
        )) {
            b.getSprite().draw(Main.getBatch());
            Vector2 direction = new Vector2(
                Main.getMain().getGame().getHero().getPosX() -Gdx.graphics.getWidth()/2f + b.getX() - 16,
                Main.getMain().getGame().getHero().getPosY() -Gdx.graphics.getHeight()/2f + b.getY() - 16
            ).nor(); //todo not working properly when walking and shooting


            b.setPosX(b.getPosX() - direction.x * 5);
            b.setPosY(b.getPosY() - direction.y * 5);
            b.updatePos();
            b.decrementTile();
            if (b.isRangeEnded()) {
                Main.getMain().getGame().getBullets().remove(b);
            }

            if (b.isCollisioned(Gdx.graphics.getWidth()/2f, Gdx.graphics.getHeight()/2f)) {
                Main.getMain().getGame().getBullets().remove(b);
                if (!Main.getMain().getGame().getHero().isInvincible()) {
                    hurt();
                }
            }
        }
    }

    public Sprite getSprite() {
        return weapon.getSprite();
    }

    private void hurt() {
        Main.getMain().getGame().getHero().removeHp(1);
        Main.getMain().getGame().getHero().setInvincible(true);
        if (Main.getMain().isSfx()) Sfx.Hurt.play();
        Timer.schedule(new Timer.Task(){
            @Override
            public void run() {
                Main.getMain().getGame().getHero().setInvincible(false);
            }
        }, 3);
    }
}
