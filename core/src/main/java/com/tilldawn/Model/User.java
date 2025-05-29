package com.tilldawn.Model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.tilldawn.Main;

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
        this.avatar = new Avatar(avatar);
    }

    public User(int id, String username, int securityQuestion, String securityAnswer) {
        this.id = id;
        this.username = username;
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
        header.add(new Label(Main.getLanguage().TMTD, skin));
        return header;
    }

    public String getUsername() {
        return username;
    }

    public String getAlert() {
        if (alert == null) {return "200";}
        return alert;
    }

    public String getSecurityQuestion() {
        return SecurityQuestions.values()[securityQuestion].getQuestion();
    }

    public String getSecurityAnswer() {
        return securityAnswer;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    public void addKills(int kills) {
        this.kills += kills;
    }

    public void  addPlaytime(int playtime) {
        this.playtime += playtime;
    }

    public void addScore(int score) {
        this.score += score;
    }

    public int getId() {
        return id;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    @Override
    public String toString() {
        return String.format("%-60s\t%-20d\t%-20d\t%-20s", username, kills, score, playtime);
    }

    public Integer getKills() {
        return kills;
    }

    public Integer getScore() {
        return score;
    }

    public Integer getPlaytime() {
        return playtime;
    }
}
