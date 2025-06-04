package com.tilldawn.Model;

import com.tilldawn.Control.EndMenuController;
import com.tilldawn.Main;
import com.tilldawn.View.EndMenuView;

import java.io.Serializable;

public class Countdown implements Serializable {
    private static final long serialVersionUID = 1L;
    private final float duration;
    private float remaining;
    private boolean running;

    public Countdown(float duration) {
        this.duration = duration * 60;
        this.remaining = duration * 60;
        this.running = false;
    }

    public void update(float delta) {
        if (running) {
            remaining -= delta;
            if (remaining < 0) {
                remaining = 0;
                running = false;
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new EndMenuView(new EndMenuController(),
                    GameAssetManager.getGameAssetManager().getSkin(), true,
                    Main.getMain().getGame().getHero()));
            }
        }
    }

    public void start() {
        running = true;
    }

    public void pause() {
        running = false;
    }

    public void resume() {
        running = true;
    }

    @Override
    public String toString() {
        return (int) remaining / 60 + "' : " + (int) remaining % 60 + "\"";
    }

    public float getDuration() {
        return duration;
    }

    public float getRemaining() {
        return remaining;
    }
}
