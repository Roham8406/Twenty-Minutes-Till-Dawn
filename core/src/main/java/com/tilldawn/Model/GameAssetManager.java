package com.tilldawn.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.tilldawn.Main;

public class GameAssetManager {
    private static GameAssetManager gameAssetManager;
    private final Skin skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));
    private final String bullet = "bullet.png";
    private final Texture backgroundTexture = new Texture(Gdx.files.internal("menubackground.jpg"));


    private GameAssetManager(){

    }

    public Texture getBackgroundTexture() {
        return backgroundTexture;
    }

    public static GameAssetManager getGameAssetManager(){
        if (gameAssetManager == null){
            gameAssetManager = new GameAssetManager();
        }
        return gameAssetManager;
    }

    public Skin getSkin() {
        return skin;
    }

    public Animation<TextureRegion> getCharacterAnimation() {
        return Main.getMain().getGame().getHero().getCharacterAnimation();
    }

    public TextureRegion getDefaultTexture() {
        return Main.getMain().getGameCharacter().getDefaultTexture();
    }


    public String getBullet(){
        return bullet;
    }
}
