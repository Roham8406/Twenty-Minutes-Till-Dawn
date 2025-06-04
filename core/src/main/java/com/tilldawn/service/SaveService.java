package com.tilldawn.service;

import com.tilldawn.Control.GameController;
import com.tilldawn.Main;
import com.tilldawn.Model.GameAssetManager;
import com.tilldawn.Model.MainGame;
import com.tilldawn.Model.Response;
import com.tilldawn.View.GameView;

import java.io.*;

public class SaveService {
    private static SaveService instance;

    public static SaveService getInstance() {
        if (instance == null) instance = new SaveService();
        return instance;
    }

    public Response save() {
        if (Main.getMain().getCurrentUser() == null) return new Response("You're not logged in.", false);
        MainGame mainGame = Main.getMain().getGame();
        String path = Main.getMain().getCurrentUser().getUsername();
        try (FileOutputStream fileOut = new FileOutputStream(path + "001.ser");
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {

            out.writeObject(mainGame);
        } catch (Exception e) {
            return new Response(e.getMessage(), false);
        }
        return new Response("Game saved successfully", true);
    }

    public Response loadObject() {
        try {
            String filePath = Main.getMain().getCurrentUser().getUsername() + "001.ser";
            File file = new File(filePath);
            if (!file.exists()) return new Response("No game saved!", false);
            FileInputStream fileIn = new FileInputStream(filePath);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Main.getMain().setGame((MainGame) in.readObject());
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new GameView(new GameController(), GameAssetManager.getGameAssetManager().getSkin()));
            return new Response("Game loaded successfully", true);
        } catch (Exception e) {
            return new Response(e.getMessage(), false);
        }
    }
}

