package com.tilldawn;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tilldawn.Control.MainMenuController;
import com.tilldawn.Model.*;
import com.tilldawn.View.MainMenuView;
import com.tilldawn.service.UserSql;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    private static Main main;
    private static SpriteBatch batch;
    private User currentUser;
    private UserSql userSql;
    private MusicTracks musicTrack = MusicTracks.Hitman;
    private boolean sfx = true;
    private boolean blackAndWhite;
    private boolean autoReload;
    private Music music;
    private Control control;
    private Float musicVolume = 0.5f;
    private GameCharacter gameCharacter = GameCharacter.Shana;

    @Override
    public void create() {
        main = this;
        batch = new SpriteBatch();
        Main.getMain().playMusic();
        control = new Control();
        getMain().setScreen(new MainMenuView(new MainMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    public static Main getMain() {
        return main;
    }

    public static void setMain(Main main) {
        Main.main = main;
    }

    public static SpriteBatch getBatch() {
        return batch;
    }

    public static void setBatch(SpriteBatch batch) {
        Main.batch = batch;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public UserSql getUserSql() {
        if (userSql == null) userSql = new UserSql();
        return userSql;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public Float getMusicVolume() {
        return musicVolume;
    }

    public MusicTracks getMusic() {
        return musicTrack;
    }

    public void setMusicVolume(Float musicVolume) {
        this.musicVolume = musicVolume;
        this.music.setVolume(musicVolume);
    }

    public void setMusic(String music) {
        this.musicTrack = MusicTracks.findMusic(music);
        this.music.pause();
        this.music.stop();
        this.music.dispose();
        playMusic();
    }

    public void playMusic() {
        music = Gdx.audio.newMusic(Gdx.files.internal(this.musicTrack.getPath()));
        music.setLooping(true);
        music.setVolume(musicVolume);
        music.play();
    }

    public boolean isAutoReload() {
        return autoReload;
    }

    public void setAutoReload(boolean autoReload) {
        this.autoReload = autoReload;
    }

    public boolean isSfx() {
        return sfx;
    }

    public void setSfx(boolean sfx) {
        this.sfx = sfx;
    }

    public boolean isBlackAndWhite() {
        return blackAndWhite;
    }

    public void setBlackAndWhite(boolean blackAndWhite) {
        this.blackAndWhite = blackAndWhite;
    }

    public Control getControl() {
        return control;
    }

    public GameCharacter getGameCharacter() {
        return gameCharacter;
    }

    public void setGameCharacter(String gameCharacter) {
        this.gameCharacter = GameCharacter.valueOf(gameCharacter);
    }
}
