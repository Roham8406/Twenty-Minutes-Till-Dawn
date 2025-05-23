package com.tilldawn.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public enum Sfx {
    Click("sfx/click.mp3"),
    Shot("sfx/shot.mp3"),
    Hurt("sfx/hurt.mp3");

    private Sfx(String path) {
        this.path = path;
    }

    private final String path;
    private Sound sound;

    public String getPath() {
        return path;
    }

    private void init() {
        if (sound == null) {
            sound = Gdx.audio.newSound(Gdx.files.internal(path));
        }
    }

    public void play() {
        init();
        sound.play();
    }
}
