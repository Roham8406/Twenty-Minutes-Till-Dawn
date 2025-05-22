package com.tilldawn.Model;

import com.tilldawn.Main;

public class MainGame {
    private Player hero;
    private int duration;
    private Weapon weapon;
    private User user;

    public MainGame() {
        hero = new Player(Main.getMain().getGameCharacter());
        duration = Main.getMain().getTime();
        weapon = new Weapon(Main.getMain().getWeaponType());
        user = Main.getMain().getCurrentUser();
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
}
