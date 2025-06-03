package com.tilldawn.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Timer;
import com.tilldawn.Control.WeaponController;
import com.tilldawn.Main;
import com.tilldawn.View.GameView;

import java.io.Serializable;

public class Weapon implements Serializable {
    private static final long serialVersionUID = 1L;

    private transient Sprite sprite;
    private transient AnimatedSprite reloadingSprite;
    WeaponType weaponType;
    private int ammo;
    private Integer damage = 0;
    private Integer projectile = 0;
    private Integer maxAmmo = 0;
    private boolean reloading = false;
    private float timeReload;

    public Weapon(WeaponType type){
        this.weaponType = type;
        this.ammo = weaponType.getAmmoMax();
        this.timeReload = weaponType.getTimeReload();
    }

    public void reload() {
        if (reloading) return;
        if (Main.getMain().isSfx()) Sfx.Reload.play();
        reloading = true;
        this.ammo = weaponType.getAmmoMax() + maxAmmo;
        ((GameView)Main.getMain().getScreen()).getController().getWorldController().setAmmo();

        Timer.schedule(new Timer.Task(){
            @Override
            public void run() {
                reloading = false;
            }
        }, timeReload);
    }

    private void init(){
        if (sprite == null){
            sprite = new Sprite(weaponType.getDefaultTexture());
            sprite.setX((float) Gdx.graphics.getWidth() / 2 - 25);
            sprite.setY((float) Gdx.graphics.getHeight() / 2 - 25);
            sprite.setSize(50,50);
            reloadingSprite = new AnimatedSprite(weaponType.getReloadAnimation());
            reloadingSprite.setX((float) Gdx.graphics.getWidth() / 2  - 12.5f);
            reloadingSprite.setY((float) Gdx.graphics.getHeight() / 2 - 12.5f);
            reloadingSprite.setSize(50,50);
        }
    }

    public Sprite getSprite() {
        init();
        if (reloading){
            return reloadingSprite;
        } else {
            return sprite;
        }
    }

    public int getAmmo() {
        return ammo;
    }

    public void setAmmo(int ammo){
        this.ammo = ammo;
    }

    public boolean isReloading() {
        return reloading;
    }

    public boolean canShoot() {
        return !isReloading() && ammo > 0;
    }

    public Integer getProjectTile() {
        return weaponType.getProjectTile() + projectile;
    }

    public Integer getDamage() {
        return weaponType.getDamage() + damage;
    }

    public void incrementProjectile() {
        projectile++;
    }

    public void incrementDamage() {
        damage = (int) (damage * 1.25);
        Timer.schedule(new Timer.Task(){
            @Override
            public void run() {
                damage = (int) (0.8 * damage);
            }
        }, 10);
    }

    public void incrementAmmo() {
        maxAmmo += 5;
    }

    public void fastReload() {
        timeReload = 0.3f;
    }
}
