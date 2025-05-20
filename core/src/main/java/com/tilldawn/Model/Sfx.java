package com.tilldawn.Model;

public enum Sfx {
    Click("sfx/click.mp3");

    private Sfx(String path) {
        this.path = path;
    }

    private final String path;

    public String getPath() {
        return path;
    }
}
