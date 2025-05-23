package com.tilldawn.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Timer;
import com.tilldawn.Control.WeaponController;

public class Weapon {
    private Sprite sprite;
    private AnimatedSprite reloadingSprite;
    WeaponType weaponType;
    private int ammo;
    private boolean reloading = false;

    public Weapon(WeaponType type){
        this.weaponType = type;
        this.ammo = weaponType.getAmmoMax();
    }

    public void reload() {
        reloading = true;
        this.ammo = weaponType.getAmmoMax();

        Timer.schedule(new Timer.Task(){
            @Override
            public void run() {
                reloading = false;
            }
        }, weaponType.getTimeReload());
    }

    private void init(){
        if (sprite == null){
            sprite = new Sprite(weaponType.getDefaultTexture());
            sprite.setX((float) Gdx.graphics.getWidth() / 2  - 25);
            sprite.setY((float) Gdx.graphics.getHeight() / 2 - 25);
            sprite.setSize(50,50);
            reloadingSprite = new AnimatedSprite(weaponType.getReloadAnimation());
            reloadingSprite.setX((float) Gdx.graphics.getWidth() / 2  - 25);
            reloadingSprite.setY((float) Gdx.graphics.getHeight() / 2 - 25);
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
        return weaponType.getProjectTile();
    }

    public Integer getDamage() {
        return weaponType.getDamage();
    }


}
