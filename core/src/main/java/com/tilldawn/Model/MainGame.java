package com.tilldawn.Model;

import com.tilldawn.Main;
import com.tilldawn.Model.enemy.Enemy;
import com.tilldawn.Model.enemy.Tree;
import com.tilldawn.Model.enemy.Wall;

import java.util.ArrayList;
import java.util.Random;

public class MainGame {
    private Player hero;
    private int duration;
    private boolean autoAim;
    private Weapon weapon;
    private User user;
    private Control control;
    private boolean autoReload ;
    private Countdown timer;
    private ArrayList<Tree> trees = new ArrayList<>();
    private ArrayList<Wall> walls = new ArrayList<>();
    private ArrayList<Float> lastSpawn = new ArrayList<>(3);
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private ArrayList<EnemyBullet> bullets = new ArrayList<>();
    private Integer vitality = 0;
    private Integer amocrease = 0;
    private Integer damager = 0;
    private Integer procrease = 0;
    private Integer speedy = 0;

    public MainGame() {
        hero = new Player(Main.getMain().getGameCharacter());
        duration = Main.getMain().getTime();
        weapon = new Weapon(Main.getMain().getWeaponType());
        user = Main.getMain().getCurrentUser();
        control = Main.getMain().getControl();
        autoReload = Main.getMain().isAutoReload();
        timer = new Countdown(Main.getMain().getTime());
        lastSpawn.add(timer.getRemaining());
        lastSpawn.add(timer.getRemaining());
        lastSpawn.add(timer.getRemaining());
        Random rand = new Random();
        int treesNumber = rand.nextInt(50, 70);
        for (int i = 0; i < treesNumber; i++) {
            trees.add(new Tree(rand.nextInt(0,3776), rand.nextInt(0, 2680), rand.nextFloat(0, 5)));
        }
        walls.add(new Wall(1888,0, rand.nextFloat(0, 5), false, true));
        walls.add(new Wall(0,1344, rand.nextFloat(0, 5), true, true));
        walls.add(new Wall(1888,2688, rand.nextFloat(0, 5), false, false));
        walls.add(new Wall(3776,1344, rand.nextFloat(0, 5), true, false));
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

    public void enableAutoReload() {
        this.autoReload = true;
    }

    public Countdown getTimer() {
        return timer;
    }

    public ArrayList<Tree> getTrees() {
        return trees;
    }

    public ArrayList<Wall> getWalls() {
        return walls;
    }

    public ArrayList<Float> getLastSpawn() {
        return lastSpawn;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public ArrayList<EnemyBullet> getBullets() {
        return bullets;
    }

    public void incrementVitality() {
        vitality++;
    }
    public void incrementAmocrease() {
        amocrease++;
    }
    public void incrementDamager() {
        damager++;
    }
    public void incrementProcrease() {
        procrease++;
    }
    public void incrementSpeedy() {
        speedy++;
    }

    public Integer getAmocrease() {
        return amocrease;
    }

    public Integer getDamager() {
        return damager;
    }

    public Integer getProcrease() {
        return procrease;
    }

    public Integer getSpeedy() {
        return speedy;
    }

    public Integer getVitality() {
        return vitality;
    }

    public boolean isAutoAim() {
        return autoAim;
    }

    public void triggerAutoAim() {
        autoAim = !autoAim;
    }
}
