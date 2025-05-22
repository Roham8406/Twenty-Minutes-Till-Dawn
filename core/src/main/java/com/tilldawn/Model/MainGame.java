package com.tilldawn.Model;

import com.tilldawn.Main;

import java.lang.ModuleLayer;

public class MainGame {
    private Player hero;
    private int duration;
    private Weapon weapon;
    private User user;
    private Control control;
    private boolean autoReload ;

    public MainGame() {
        hero = new Player(Main.getMain().getGameCharacter());
        duration = Main.getMain().getTime();
        weapon = new Weapon(Main.getMain().getWeaponType());
        user = Main.getMain().getCurrentUser();
        control = Main.getMain().getControl();
        autoReload = Main.getMain().isAutoReload();
    }

    public Player getHero() {
        return hero;
    }

    public int getDuration() {
        return duration;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public User getUser() {
        return user;
    }

    public Control getControl() {
        return control;
    }

    public boolean isAutoReload() {
        return autoReload;
    }
}
