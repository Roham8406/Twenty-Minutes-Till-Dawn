package com.tilldawn.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public enum GameCharacter {
    Shana("Shana", "T/T_Shana_Portrait.png", "T/T_Shana.png", 4, 4),
    Diamond("Diamond", "T/T_Diamond_Portrait.png", "T/T_Diamond #7829.png", 1, 7),
    Scarlet("Scarlet", "T/T_Scarlett_Portrait.png", "T/T_Scarlett.png", 5, 3),
    Lilith("Lilith", "T/T_Lilith_Portrait.png", "T/T_Lilith.png", 3, 5),
    Dasher("Dasher", "T/T_Dasher_Portrait.png", "T/T_Dasher.png", 10, 2);

    private String name;
    private String portrait;
    private String frames;
    private Integer speed;
    private Integer health;
    private Texture texture;
    TextureRegion[][] textureFrames;
    Animation<TextureRegion> characterAnimation;


    GameCharacter(String name, String portrait, String frames, Integer speed, Integer health) {
        this.name = name;
        this.portrait = portrait;
        this.frames = frames;
        this.speed = speed;
        this.health = health;
    }

    private void init() {
        if (texture == null) {
            texture = new Texture(frames);
            textureFrames = TextureRegion.split(texture, 32, 32);
            characterAnimation = new Animation<>(0.1f, textureFrames[0][0], textureFrames[0][1], textureFrames[0][2],
                textureFrames[0][2], textureFrames[0][3], textureFrames[0][4], textureFrames[0][5]);
        }
    }

    public TextureRegion getDefaultTexture() {
        init();
        return textureFrames[0][0];
    }

    public Animation<TextureRegion> getCharacterAnimation() {
        init();
        return characterAnimation;
    }

    public ImageButton getPortraitButton() {
        init();
        Texture buttonTexture = new Texture(Gdx.files.internal(portrait));
        TextureRegionDrawable drawable = new TextureRegionDrawable(new TextureRegion(buttonTexture));
        return new ImageButton(drawable);
    }
}
