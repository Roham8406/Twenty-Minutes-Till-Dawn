package com.tilldawn.Model;

import com.badlogic.gdx.utils.Timer;
import com.tilldawn.Main;
import com.tilldawn.Model.enemy.Tree;

import java.lang.ModuleLayer;
import java.util.ArrayList;
import java.util.Random;

public class MainGame {
    private Player hero;
    private int duration;
    private Weapon weapon;
    private User user;
    private Control control;
    private boolean autoReload ;
    private Countdown timer;
    private ArrayList<Tree> trees = new ArrayList<>();

    public MainGame() {
        hero = new Player(Main.getMain().getGameCharacter());
        duration = Main.getMain().getTime();
        weapon = new Weapon(Main.getMain().getWeaponType());
        user = Main.getMain().getCurrentUser();
        control = Main.getMain().getControl();
        autoReload = Main.getMain().isAutoReload();
        timer = new Countdown(Main.getMain().getTime());
        Random rand = new Random();
        int treesNumber = rand.nextInt(50, 70);
        for (int i = 0; i < treesNumber; i++) {
            trees.add(new Tree(rand.nextInt(0,3776), rand.nextInt(0, 2680), rand.nextFloat(0, 5)));
        }
    }

    public Player getHero() {
        return hero;
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

    public Countdown getTimer() {
        return timer;
    }

    public ArrayList<Tree> getTrees() {
        return trees;
    }
}
