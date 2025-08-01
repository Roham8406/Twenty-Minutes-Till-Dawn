package com.tilldawn.Control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.tilldawn.Main;
import com.tilldawn.Model.*;
import com.tilldawn.Model.enemy.Enemy;

public class WeaponController {
    private final Weapon weapon;
    private final WorldController worldController;

    public WeaponController(WorldController worldController, Weapon weapon) {
        this.weapon = weapon;
        this.worldController = worldController;
    }

    public void update(float delta) {
        weapon.getSprite().draw(Main.getBatch());
        if (weapon.isReloading()) {
            ((AnimatedSprite) weapon.getSprite()).update(delta);
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

    public void handleWeaponShoot(int x, int y) {
        if (weapon.canShoot()) {
            worldController.removeAmmo();
            for (int i = 0; i < weapon.getProjectTile(); i++) {
                Main.getMain().getGame().getBullets().add(new Bullet(
                    x, y,
                    i - weapon.getProjectTile() / 2f,
                    weapon.getDamage())
                );
            }
            weapon.setAmmo(weapon.getAmmo() - 1);
            if (Main.getMain().isSfx()) Sfx.Shot.play();
            if (Main.getMain().getGame().isAutoReload() && weapon.getAmmo() == 0) {
                weapon.reload();
            }
        } else {
            if (Main.getMain().isSfx()) Sfx.EmptyShot.play();
        }
    }

    public void updateBullets() {
        for (Bullet b : Main.getMain().getGame().getBullets().toArray(new Bullet[0])) {
            b.getSprite().draw(Main.getBatch());
            Vector2 direction = b.getVelocity();

            for (Enemy enemy : Main.getMain().getGame().getEnemies().toArray(new Enemy[0])) {
                if (enemy.isDead()) continue;
                if (enemy.isCollided(b.getSprite().getX(), b.getSprite().getY())) {
                    enemy.removeHp(b.getDamage());
                    enemy.goBack(direction);
                    Main.getMain().getGame().getBullets().remove(b);
                }
            }

            b.setPosX(b.getPosX() + direction.x * 10);
            b.setPosY(b.getPosY() + direction.y * 10);
            b.updatePos();
            b.decrementTile();
            if (b.isRangeEnded()) {
                Main.getMain().getGame().getBullets().remove(b);
            }
        }
        for (EnemyBullet b : Main.getMain().getGame().getEnemyBullets().toArray(new EnemyBullet[0])) {
            b.getSprite().draw(Main.getBatch());
            Vector2 direction = new Vector2(
                Main.getMain().getGame().getHero().getPosX() - Gdx.graphics.getWidth() / 2f + b.getX() - 16,
                Main.getMain().getGame().getHero().getPosY() - Gdx.graphics.getHeight() / 2f + b.getY() - 16
            ).nor();


            b.setPosX(b.getPosX() - direction.x * 5);
            b.setPosY(b.getPosY() - direction.y * 5);
            b.updatePos();
            b.decrementTile();
            if (b.isRangeEnded()) {
                Main.getMain().getGame().getEnemyBullets().remove(b);
            }

            if (b.isCollisioned(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f)) {
                Main.getMain().getGame().getEnemyBullets().remove(b);
                if (!Main.getMain().getGame().getHero().isInvincible()) {
                    worldController.shotted();
                    worldController.hurt();
                }
            }
        }
    }

    public Sprite getSprite() {
        return weapon.getSprite();
    }
}
