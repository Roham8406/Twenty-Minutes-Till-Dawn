package com.tilldawn.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.tilldawn.Main;

public class GameAssetManager {
    private static GameAssetManager gameAssetManager;
    private final Skin skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));
    private final String bullet = "bullet.png";


    private GameAssetManager(){

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
