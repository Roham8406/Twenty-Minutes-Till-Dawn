package com.tilldawn.Model;

public enum MusicTracks {
    Hitman("Hitman","tracks/Hitman.mp3"),
    Adventure("Adventure","tracks/Adventure.mp3");

    private MusicTracks(String trackName, String path) {
        this.trackName = trackName;
        this.path = path;
    }

    private final String trackName;
    private final String path;

    public static MusicTracks findMusic(String music) {
        for (MusicTracks value : MusicTracks.values()) {
            if (value.getTrackName().equals(music)) {return value;}
        }
        return null;
    }

    public String getTrackName() {
        return trackName;
    }

    public String getPath() {
        return path;
    }
}
