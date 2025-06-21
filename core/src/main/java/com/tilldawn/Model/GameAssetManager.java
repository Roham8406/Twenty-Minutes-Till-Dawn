package com.tilldawn.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class GameAssetManager {
    private static GameAssetManager gameAssetManager;
    private final Skin skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));
    private final Texture bullet = new Texture("bullet.png");
    private final Animation<TextureRegion> elderAnimation;
    private final Animation<TextureRegion> eyeBatAnimation;
    private final Animation<TextureRegion> tentacleSpawningAnimation;
    private final Animation<TextureRegion> tentacleAnimation;
    private final Animation<TextureRegion> deadEnemyAnimation;
    private final Animation<TextureRegion> deathAnimation;
    private final Texture backgroundTexture = new Texture(Gdx.files.internal("menubackground.jpg"));


    private GameAssetManager() {
        TextureRegion[][] elder = TextureRegion.split(
            new Texture("T/T_ShubNiggurath.png"), 96, 96
        );
        elderAnimation = new Animation<>(0.3f, elder[0][0], elder[0][1], elder[0][2]);
        TextureRegion[][] eyeBat = TextureRegion.split(
            new Texture("T/T_EyeBat.png"), 96, 96
        );
        eyeBatAnimation = new Animation<>(0.3f, eyeBat[0][0], eyeBat[0][1], eyeBat[0][2], eyeBat[0][3]);
        TextureRegion[][] tentacle = TextureRegion.split(
            new Texture("T/T_TentacleEnemy.png"), 64, 64
        );
        tentacleSpawningAnimation = new Animation<>(0.3f, tentacle[0][0], tentacle[0][1], tentacle[0][2]);
        tentacleAnimation = new Animation<>(0.3f, tentacle[2][0], tentacle[2][1], tentacle[2][2], tentacle[2][3]);
        TextureRegion[][] deadEnemy = TextureRegion.split(
            new Texture("T/T_LunaBlackHoleDamage_0.png"), 42, 42
        );
        deadEnemyAnimation = new Animation<>(0.3f, deadEnemy[0][0], deadEnemy[0][0]);
        TextureRegion[][] death = TextureRegion.split(
            new Texture("T/T_DeathFX.png"), 40, 40
        );
        deathAnimation = new Animation<>(0.1f, death[0][0], death[0][1], death[0][2], death[0][3]);
    }

    public Texture getBackgroundTexture() {
        return backgroundTexture;
    }

    public static GameAssetManager getGameAssetManager() {
        if (gameAssetManager == null) {
            gameAssetManager = new GameAssetManager();
        }
        return gameAssetManager;
    }

    public Skin getSkin() {
        return skin;
    }

    public Texture getBullet() {
        return bullet;
    }

    public AnimatedSprite getElder() {
        return new AnimatedSprite(elderAnimation);
    }

    public AnimatedSprite getEyeBat() {
        return new AnimatedSprite(eyeBatAnimation);
    }

    public AnimatedSprite getTentacle() {
        return new AnimatedSprite(tentacleAnimation);
    }

    public AnimatedSprite getTentacleSpawning() {
        return new AnimatedSprite(tentacleSpawningAnimation);
    }

    public AnimatedSprite getDeadEnemy() {
        return new AnimatedSprite(deadEnemyAnimation);
    }

    public  AnimatedSprite getDeath() {
        return new AnimatedSprite(deathAnimation);
    }
}
