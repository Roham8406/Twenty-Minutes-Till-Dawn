package com.tilldawn.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;

import java.util.Random;

public class Avatar {
    private final String avatar;
    private final String avatar1 = "avatars/1.png";
    private final String avatar2 = "avatars/2.png";
    private final String avatar3 = "avatars/3.png";
    private final String avatar4 = "avatars/4.png";
    private final String avatar5 = "avatars/5.png";


    public Avatar() {
        String[] defaultAvatars = {avatar1, avatar2, avatar3, avatar4, avatar5};
        avatar = defaultAvatars[new Random().nextInt(defaultAvatars.length)];
    }

    public Avatar(String avatar) {
        this.avatar = avatar;
    }

    public Array<Avatar> getDefaultAvatars() {
        String[] defaultAvatars = {avatar, avatar1, avatar2, avatar3, avatar4, avatar5};
        Array<Avatar> avatars = new Array<>();
        for (String defaultAvatar : defaultAvatars) {
            avatars.add(new Avatar(defaultAvatar));
        }
        return avatars;
    }

    public String getAvatar() {
        return avatar;
    }

    public ImageButton getActor(Skin skin) {
        Texture buttonTexture = new Texture(Gdx.files.internal(avatar));
        TextureRegionDrawable drawable = new TextureRegionDrawable(new TextureRegion(buttonTexture));
        return new ImageButton(drawable);
    }

    @Override
    public String toString() {
        return avatar;
    }

    public void getAvatars(Table chooseAvatar) {
        String[] defaultAvatars = {avatar, avatar1, avatar2, avatar3, avatar4, avatar5};
        for (String defaultAvatar : defaultAvatars) {
            Texture buttonTexture = new Texture(Gdx.files.internal(defaultAvatar));
            TextureRegionDrawable drawable = new TextureRegionDrawable(new TextureRegion(buttonTexture));
            ImageButton imageButton = new ImageButton(drawable);
            chooseAvatar.add(imageButton).width(70).padRight(30);
        }
    }
}
