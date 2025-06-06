package com.tilldawn.Model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.tilldawn.Main;

public enum WeaponType {
    Revolver(Main.getLanguage().Revolver, "T/T_Revolver_SS.png", 6, 1, 1, 20),
    Shotgun(Main.getLanguage().Shotgun, "T/T_Shotgun_SS.png", 2, 1, 4, 10),
    Smg(Main.getLanguage().DualSMGs, "T/T_DualSMGs_SS.png", 24, 2, 1, 8);

    private final String name;
    private final String frames;
    private final Integer ammoMax;
    private final Integer timeReload;
    private final Integer damage;
    private final Integer projectTile;
    private Texture texture;
    TextureRegion[][] textureFrames;
    Animation<TextureRegion> reloadAnimation;


    WeaponType(String name, String frames, Integer ammoMax, Integer timeReload, Integer projectTile, Integer damage) {
        this.name = name;
        this.frames = frames;
        this.ammoMax = ammoMax;
        this.timeReload = timeReload;
        this.damage = damage;
        this.projectTile = projectTile;
    }

    private void init() {
        if (texture == null) {
            texture = new Texture(frames);
            textureFrames = TextureRegion.split(texture, 16, 16);
            reloadAnimation = new Animation<>(0.1f, textureFrames[0][1], textureFrames[0][2],
                textureFrames[0][3], textureFrames[0][4]);
        }
    }

    public TextureRegion getDefaultTexture() {
        init();
        return textureFrames[0][0];
    }

    public Animation<TextureRegion> getReloadAnimation() {
        init();
        return reloadAnimation;
    }

    public ImageButton getPortraitButton() {
        init();
        TextureRegionDrawable drawable = new TextureRegionDrawable(textureFrames[0][0]);
        ImageButton button = new ImageButton(drawable);
        button.setSize(100, 100);
        return button;
    }

    public String getName() {
        return name;
    }

    public Integer getAmmoMax() {
        return ammoMax;
    }

    public Integer getTimeReload() {
        return timeReload;
    }

    public Integer getProjectTile() {
        return projectTile;
    }

    public Integer getDamage() {
        return damage;
    }
}
