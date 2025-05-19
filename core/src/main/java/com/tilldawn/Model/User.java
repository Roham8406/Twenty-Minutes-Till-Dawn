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
    private String alert;
    private Integer kills;
    private Integer playtime;
    private Integer score;
    private Integer securityQuestion;
    private String securityAnswer;
    private String email;
    private Avatar avatar;

    public User(String username, String alert) {
        this.username = username;
        this.alert = alert;
    }

    public User(int id, String username, int kills, int playtime, int score, String avatar) {
        this.id = id;
        this.username = username;
        this.kills = kills;
        this.playtime = playtime;
        this.score = score;
        this.avatar = Avatar.getAvatar(avatar);
    }

    public User(int id, String username, int kills, int playtime, int score, String avatar, int securityQuestion, String securityAnswer) {
        this.id = id;
        this.username = username;
        this.kills = kills;
        this.playtime = playtime;
        this.score = score;
        this.avatar = Avatar.getAvatar(avatar);
        this.securityQuestion = securityQuestion;
        this.securityAnswer = securityAnswer;
    }

    public Table createHeader(Skin skin) {
        Table header = new Table();
        header.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture("background.png"))));
        header.left();
        header.add(new Label(username, skin)).padRight(50);
        header.add(new Label(score.toString(), skin)).padRight(50);
        header.add(avatar.getActor(skin)).height(80);
        return header;
    }

    public static Table createUnloggedHeader(Skin skin) {
        Table header = new Table();
        header.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture("background.png"))));
        header.add(new Label("20 Minutes Till Dawn", skin));
        return header;
    }

    public String getUsername() {
        return username;
    }

    public String getAlert() {
        if (alert == null) {return "200";}
        return alert;
    }
}
