package com.tilldawn.Model;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import java.util.Random;

public class Avatar {
    private String avatar;
    private String avatar1 = "avatars/1.png";
    private String avatar2 = "avatars/2.png";
    private String avatar3 = "avatars/3.png";
    private String avatar4 = "avatars/4.png";
    private String avatar5 = "avatars/5.png";

    public static Avatar getAvatar(String avatar) {
        return new Avatar(); //TODO
    }

    public Avatar() {
        String[] defaultAvatars = {avatar1, avatar2, avatar3, avatar4, avatar5};
        avatar = defaultAvatars[new Random().nextInt(defaultAvatars.length)];
    }

    public String getAvatar() {
        return avatar;
    }

    public TextButton getActor(Skin skin) {
        return new TextButton("Avatar", skin);
//        return new TextButton(type, skin);
    }
}
