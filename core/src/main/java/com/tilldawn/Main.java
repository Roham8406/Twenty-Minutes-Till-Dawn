package com.tilldawn;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.tilldawn.Control.MainMenuController;
import com.tilldawn.Model.GameAssetManager;
import com.tilldawn.Model.User;
import com.tilldawn.View.MainMenuView;
import com.tilldawn.service.UserSql;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    private static Main main;
    private static SpriteBatch batch;
    private User currentUser;
    private UserSql userSql;

    @Override
    public void create() {
        main = this;
        batch = new SpriteBatch();
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
}
