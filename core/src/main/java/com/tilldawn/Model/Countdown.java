package com.tilldawn.Model;

public class Countdown {
    private float duration;
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


    public boolean isFinished() {
        return remaining <= 0;
    }

    @Override
    public String toString() {
        return (int)remaining/60 + "' : " + (int)remaining%60 + "\"";
    }

    public float getDuration() {
        return duration;
    }

    public float getRemaining() {
        return remaining;
    }
}
