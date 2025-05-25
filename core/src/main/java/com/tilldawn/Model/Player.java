package com.tilldawn.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Timer;
import com.tilldawn.Control.AbilityMenuController;
import com.tilldawn.Control.EndMenuController;
import com.tilldawn.Control.GameController;
import com.tilldawn.Main;
import com.tilldawn.View.AbilityMenuView;
import com.tilldawn.View.EndMenuView;

public class Player {
    private TextureRegion playerTexture;
    private Sprite playerSprite;
    private float posX = 0;
    private float posY = 0;
    private CollisionRect rect ;
    private float time = 0;
    private float speed;
    private int hp;
    private boolean isInvincible;
    private int level;
    private int xp;
    private int maxHp;
    private int kills;
    private final GameCharacter gameCharacter;

    public float getSpeed() {
        return speed;
    }

    private boolean isPlayerIdle = true;
    private boolean isPlayerRunning = false;

    public Player(GameCharacter gameCharacter){
        playerTexture = gameCharacter.getDefaultTexture();
        playerSprite = new Sprite(playerTexture);
        playerSprite.setPosition((float) Gdx.graphics.getWidth() / 2, (float) Gdx.graphics.getHeight() / 2);
        playerSprite.setSize(playerTexture.getRegionWidth() * 3, playerTexture.getRegionHeight() * 3);
        rect = new CollisionRect((float) Gdx.graphics.getWidth() / 2, (float) Gdx.graphics.getHeight(), playerTexture.getRegionWidth() * 3, playerTexture.getRegionHeight() * 3);
        this.gameCharacter = gameCharacter;
        hp = gameCharacter.getHealth();
        maxHp = gameCharacter.getHealth();
        speed = gameCharacter.getSpeed();
        isInvincible = false;
        level = 1;
    }

    public TextureRegion getPlayerTexture() {
        return playerTexture;
    }

    public void setPlayerTexture(TextureRegion playerTexture) {
        this.playerTexture = playerTexture;
    }

    public Sprite getPlayerSprite() {
        return playerSprite;
    }

    public void setPlayerSprite(Sprite playerSprite) {
        this.playerSprite = playerSprite;
    }

    public float getPosX() {
        return posX;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public float getPosY() {
        return posY;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }

    public float getPlayerHealth() {
        return hp;
    }

    public CollisionRect getRect() {
        return rect;
    }

    public void setRect(CollisionRect rect) {
        this.rect = rect;
    }


    public boolean isPlayerIdle() {
        return isPlayerIdle;
    }

    public void setPlayerIdle(boolean playerIdle) {
        isPlayerIdle = playerIdle;
    }

    public boolean isPlayerRunning() {
        return isPlayerRunning;
    }

    public void setPlayerRunning(boolean playerRunning) {
        isPlayerRunning = playerRunning;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public void removeHp(int hp) {
        this.hp -= hp;
        if (this.hp <= 0) {
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new EndMenuView(new EndMenuController(), GameAssetManager.getGameAssetManager().getSkin(), false, this));
        }
    }

    public void setInvincible(boolean invincible) {
        isInvincible = invincible;
    }

    public boolean isInvincible() {
        return isInvincible;
    }

    public Animation<TextureRegion> getCharacterAnimation() {
        return gameCharacter.getCharacterAnimation();
    }

    public void incrementXp(GameController gameController) {
        incrementXp(gameController, 3);
    }

    private void incrementXp(GameController gameController, int XP) {
        xp += XP;
        if (xp >= level*20) {
            xp -= level*20;
            level++;
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new AbilityMenuView(new AbilityMenuController(),
                GameAssetManager.getGameAssetManager().getSkin(), gameController, level));
        }
    }

    public int getXp() {
        return xp;
    }

    public void incrementMaxHp() {
        maxHp++;
        hp++;
    }

    public void incrementHp() {
        if (hp < maxHp) hp++;
    }

    public void incrementSpeed () {
        speed *= 2;
        Timer.schedule(new Timer.Task(){
            @Override
            public void run() {
                speed /= 2;
            }
        }, 10);
    }

    public void incrementKills() {
        kills++;
    }

    public int getKills() {
        return kills;
    }

    public int getScore() {
        return level * (level - 1) * 10 + xp;
    }

    public void addLevel(GameController gameController) {
        incrementXp(gameController, level * 20 - xp);
    }
}
