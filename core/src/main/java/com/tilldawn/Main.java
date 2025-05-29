package com.tilldawn;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
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
    private Float musicVolume = 0.1f;
    private WeaponType weaponType = WeaponType.Revolver;
    private Integer time = 2;
    private GameCharacter gameCharacter = GameCharacter.Shana;
    private MainGame mainGame;
    private ShaderProgram shader;
    private FrameBuffer fbo;
    private TextureRegion fboRegion;
    private Language language = Language.English;


    @Override
    public void create() {
        ShaderProgram.pedantic = false; // ignore strict GLSL checks

        shader = new ShaderProgram(
            Gdx.files.internal("Glsl/grayscale.vert"),
            Gdx.files.internal("Glsl/grayscale.frag")
        );

        fbo = new FrameBuffer(Pixmap.Format.RGBA8888, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false);
        fboRegion = new TextureRegion(fbo.getColorBufferTexture());
        fboRegion.flip(false, true); // flip Y


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

    public void setGameCharacter(GameCharacter gameCharacter) {
        this.gameCharacter = gameCharacter;
    }

    public WeaponType getWeaponType() {
        return weaponType;
    }


    public Integer getTime() {
        return time;
    }

    public void setWeaponType(WeaponType weaponType) {
        this.weaponType = weaponType;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public MainGame getGame() {
        return mainGame;
    }

    public void setGame(MainGame mainGame) {
        this.mainGame = mainGame;
    }

    public void startBatch() {
        Main.getMain().fbo.begin();
        if (blackAndWhite) {
            Gdx.gl.glClearColor(1, 1, 1, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        }
        Main.getBatch().begin();
    }

    public void endBatch() {
        Main.getBatch().end();
        Main.getMain().fbo.end();
        if (blackAndWhite) {
            Gdx.gl.glClearColor(0, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            Main.getBatch().setShader(Main.getMain().shader);
        }
        Main.getBatch().begin();
        Main.getBatch().draw(Main.getMain().fboRegion, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Main.getBatch().end();
        Main.getBatch().setShader(null);
    }

    public Language getLang() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = Language.findLanguage(language);
    }

    public static Language getLanguage() {
        if (main == null) return Language.English;
        return getMain().getLang();
    }
}
