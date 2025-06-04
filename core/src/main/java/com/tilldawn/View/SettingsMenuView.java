package com.tilldawn.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tilldawn.Control.SettingMenuController;
import com.tilldawn.Main;
import com.tilldawn.Model.Language;
import com.tilldawn.Model.MusicTracks;

import java.util.Arrays;

public class SettingsMenuView implements Screen {
    private Stage stage;
    private final Label musicVolumeLabel;
    private final Slider musicVolume;
    private final Label musicTrackLabel;
    private final SelectBox<String> musicTrack;
    private final Label languageLabel;
    private final SelectBox<String> language;
    private final CheckBox sfx;
    private final CheckBox blackAndWhite;
    private final CheckBox autoReload;
    private final TextButton controlSettings;
    private final TextButton mainMenu;
    public Table table;
    private final SettingMenuController controller;

    public SettingsMenuView(SettingMenuController controller, Skin skin) {
        this.controller = controller;

        this.musicVolumeLabel = new Label(Main.getLanguage().MusicVolume, skin);
        this.languageLabel = new Label("Language", skin);
        this.musicTrackLabel = new Label(Main.getLanguage().MusicTrack, skin);
        this.musicTrack = new SelectBox<String>(skin);
        this.musicTrack.setItems(Arrays.stream(MusicTracks.values())
            .map(MusicTracks::getTrackName)
            .toArray(String[]::new));
        this.musicTrack.setSelected(Main.getMain().getMusic().getTrackName());
        this.musicVolume = new Slider(0, 1f, 0.01f, false, skin);
        this.musicVolume.setValue(Main.getMain().getMusicVolume());
        this.language = new SelectBox<String>(skin);
        this.language.setItems(Arrays.stream(Language.values())
            .map(Language::getName)
            .toArray(String[]::new));
        this.language.setSelected(Main.getMain().getLang().getName());
        this.sfx = new CheckBox(Main.getLanguage().SFXs, skin);
        this.blackAndWhite = new CheckBox(Main.getLanguage().Grayscale, skin);
        this.controlSettings = new TextButton(Main.getLanguage().ControlSetting, skin);
        this.autoReload = new CheckBox(Main.getLanguage().AutoReload, skin);
        this.mainMenu = new TextButton(Main.getLanguage().MainMenu, skin);
        this.table = new Table();

        controller.setView(this);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table.setFillParent(true);
        table.center();

        table.add(languageLabel).width(200).padRight(30);
        table.add(language).colspan(2).width(500);
        table.row().pad(10, 0 , 10, 0);
        table.add(musicVolumeLabel).width(200).padRight(30);
        table.add(musicVolume).colspan(2).width(500);
        table.row().pad(10, 0 , 10, 0);
        table.add(musicTrackLabel).width(200).padRight(30);
        table.add(musicTrack).colspan(2).width(500);
        table.row().pad(10, 0 , 10, 0);
        table.add(sfx).width(400).padRight(50);
        table.add(blackAndWhite).width(600).padRight(50);
        table.add(autoReload).width(500);
        if (Main.getMain().isSfx()) sfx.setChecked(true);
        if (Main.getMain().isBlackAndWhite()) blackAndWhite.setChecked(true);
        if (Main.getMain().isAutoReload()) autoReload.setChecked(true);
        table.row().pad(10, 0 , 10 , 0);
        table.add(controlSettings).colspan(3).width(500);
        table.row().pad(10, 0 , 10 , 0);
        table.add(mainMenu).colspan(3).width(500);

        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        Main.getMain().startBatch();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        Main.getMain().endBatch();
        controller.handleMainMenuButtons();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    public TextButton getSfx() {
        return sfx;
    }

    public TextButton getBlackAndWhite() {
        return blackAndWhite;
    }

    public TextButton getAutoReload() {
        return autoReload;
    }

    public SelectBox<String> getMusicTrack() {
        return musicTrack;
    }

    public SelectBox<String> getLanguage() {
        return language;
    }

    public Slider getMusicVolume() {
        return musicVolume;
    }

    public TextButton getControlSettings() {
        return controlSettings;
    }

    public TextButton getMainMenu() {
        return mainMenu;
    }

    public Stage getStage() {
        return stage;
    }
}
