package com.tilldawn.Control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.tilldawn.Main;
import com.tilldawn.Model.AnimatedSprite;
import com.tilldawn.Model.Bullet;
import com.tilldawn.Model.Sfx;
import com.tilldawn.Model.Weapon;

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
            bullets.add(new Bullet(x, y, weapon.getProjectTile(), weapon.getDamage()));
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
            ).nor();

            b.getSprite().setX(b.getSprite().getX() - direction.x * 5);
            b.getSprite().setY(b.getSprite().getY() + direction.y * 5);
            b.decrementTile();
            if (b.isRangeEnded()) {
                bullets.remove(b);
            }
        }
    }

    public Sprite getSprite() {
        return weapon.getSprite();
    }
}
