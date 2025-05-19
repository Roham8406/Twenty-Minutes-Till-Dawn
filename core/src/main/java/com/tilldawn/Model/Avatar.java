package com.tilldawn.Model;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class Avatar {
    private String type = "1";

    public static Avatar getAvatar(String avatar) {
        return new Avatar(); //TODO
    }

    public TextButton getActor(Skin skin) {
        return new TextButton("Avatar", skin);
//        return new TextButton(type, skin);
    }
}
