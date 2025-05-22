package com.tilldawn.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Weapon {
    private Sprite sprite;
    WeaponType weaponType = WeaponType.Shotgun;
    private int ammo = 30;

    public Weapon(){

    }

    private void init(){
        if (sprite == null){
            sprite = new Sprite(weaponType.getDefaultTexture());
            sprite.setX((float) Gdx.graphics.getWidth() / 2  - 25);
            sprite.setY((float) Gdx.graphics.getHeight() / 2 - 25);
            sprite.setSize(50,50);
        }
    }

    public Sprite getSprite() {
        init();
        return sprite;
    }

    public int getAmmo() {
        return ammo;
    }

    public void setAmmo(int ammo){
        this.ammo = ammo;
    }
}
