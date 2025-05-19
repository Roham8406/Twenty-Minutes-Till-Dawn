package com.tilldawn.Model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class User {
    private int id;
    private String username;
    private String password;
    private String email;
    private Avatar avatar;
    private Integer score;

    public Table createHeader(Skin skin) {
        Table header = new Table();
        header.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture("background.png"))));
        header.add(new Label(username, skin));
        header.add(new Label(score.toString(), skin));
        header.add(avatar.getActor(skin));
        return header;
    }

    public static Table createUnloggedHeader(Skin skin) {
        Table header = new Table();
        header.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture("background.png"))));
        header.add(new Label("20 Minutes Till Dawn", skin));
        return header;
    }
}
