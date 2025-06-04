package com.tilldawn.Control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.tilldawn.Main;
import com.tilldawn.Model.GameAssetManager;
import com.tilldawn.Model.Player;
import com.tilldawn.Model.enemy.Elder;
import com.tilldawn.View.PauseMenuView;

public class PlayerController {
    private Player player;
    private GameController gameController;

    public PlayerController(Player player, GameController gameController) {
        this.player = player;
        this.gameController = gameController;
    }

    public void update() {
        player.getPlayerSprite().draw(Main.getBatch());

        if (player.isPlayerIdle()) {
            idleAnimation();
        }

        handlePlayerInput();
    }


    private boolean decodeKey(String string, boolean hold) {
        return hold ? (Gdx.input.isButtonPressed(Main.getMain().getGame().getControl().getKeys().get(string)) ||
            Gdx.input.isKeyPressed(Main.getMain().getGame().getControl().getKeys().get(string))) :
            (Gdx.input.isButtonJustPressed(Main.getMain().getGame().getControl().getKeys().get(string)) ||
                Gdx.input.isKeyJustPressed(Main.getMain().getGame().getControl().getKeys().get(string)));
    }

    public void handlePlayerInput() {
        if (decodeKey("shoot", false)) {
            gameController.getWeaponController().handleWeaponShoot(
                Gdx.input.getX(),
                Gdx.input.getY()
            );
        }
        boolean flag = true;
        if (decodeKey("up", true)) {
            player.setPosY(player.getPosY() - player.getSpeed());
            player.setPlayerRunning(true);
            flag = false;
        }
        if (decodeKey("right", true)) {
            player.setPosX(player.getPosX() - player.getSpeed());
            player.setPlayerRunning(true);
            flag = false;
        }
        if (decodeKey("down", true)) {
            player.setPosY(player.getPosY() + player.getSpeed());
            player.setPlayerRunning(true);
            flag = false;
        }
        if (decodeKey("left", true)) {
            player.setPosX(player.getPosX() + player.getSpeed());
            player.setPlayerRunning(true);
            flag = false;
            player.getPlayerSprite().flip(true, false);
        }
        if (decodeKey("reload", false)) {
            Main.getMain().getGame().getWeapon().reload();
        }
        if (decodeKey("autoAim", false)) {
            Main.getMain().getGame().triggerAutoAim();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new PauseMenuView(new PauseMenuController(), GameAssetManager.getGameAssetManager().getSkin(), gameController));
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) {
            Main.getMain().getGame().getTimer().update(60);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)) {
            Main.getMain().getGame().getHero().incrementHp(gameController);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_3)) {
            Main.getMain().getGame().getHero().addLevel(gameController);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_4)) {
            Elder.setSpawn();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_5)) {
            Main.getMain().getGame().getWeapon().fastReload();
            Main.getMain().getGame().enableAutoReload();
        }

        if (flag && player.isPlayerRunning()) {
            player.setPlayerRunning(false);
        }
    }


    public void idleAnimation() {
        Animation<TextureRegion> animation = Main.getMain().getGame().getHero().getCharacterAnimation();
        Sprite sprite = player.getPlayerSprite();
        sprite.setRegion(animation.getKeyFrame(player.getTime()));

        if (!animation.isAnimationFinished(player.getTime())) {
            player.setTime(player.getTime() + Gdx.graphics.getDeltaTime());
        } else {
            player.setTime(0);
        }

        animation.setPlayMode(Animation.PlayMode.LOOP);
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
