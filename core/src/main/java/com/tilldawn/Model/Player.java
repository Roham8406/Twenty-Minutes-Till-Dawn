package com.tilldawn.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.tilldawn.Control.GameController;
import com.tilldawn.Main;

import java.util.ArrayList;

public class Player {
    private TextureRegion playerTexture;
    private Sprite playerSprite;
    private float posX = 0;
    private float posY = 0;
    private float playerHealth = 100;
    private CollisionRect rect ;
    private float time = 0;
    private float speed = 5;
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
        return playerHealth;
    }

    public void setPlayerHealth(float playerHealth) {
        this.playerHealth = playerHealth;
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


    public Animation<TextureRegion> getCharacterAnimation() {
        return gameCharacter.getCharacterAnimation();
    }
}
