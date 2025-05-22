package com.tilldawn.Control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.tilldawn.Main;
import com.tilldawn.Model.GameAssetManager;
import com.tilldawn.Model.Player;

public class PlayerController {
    private Player player;

    public PlayerController(Player player){
        this.player = player;
    }

    public void update(){
        player.getPlayerSprite().draw(Main.getBatch());

        if(player.isPlayerIdle()){
            idleAnimation();
        }

        handlePlayerInput();
    }


    public void handlePlayerInput(){
        if (Gdx.input.isKeyPressed(Main.getMain().getGame().getControl().getKeys().get("up"))){
            player.setPosY(player.getPosY() - player.getSpeed());
        }
        if (Gdx.input.isKeyPressed(Main.getMain().getGame().getControl().getKeys().get("right"))){
            player.setPosX(player.getPosX() - player.getSpeed());
        }
        if (Gdx.input.isKeyPressed(Main.getMain().getGame().getControl().getKeys().get("down"))){
            player.setPosY(player.getPosY() + player.getSpeed());
        }
        if (Gdx.input.isKeyPressed(Main.getMain().getGame().getControl().getKeys().get("left"))){
            player.setPosX(player.getPosX() + player.getSpeed());
            player.getPlayerSprite().flip(true, false);
        }
        if (Gdx.input.isKeyPressed(Main.getMain().getGame().getControl().getKeys().get("reload"))){
            Main.getMain().getGame().getWeapon().reload();
        }
    }


    public void idleAnimation(){
        Animation<TextureRegion> animation = Main.getMain().getGame().getHero().getCharacterAnimation();

        player.getPlayerSprite().setRegion(animation.getKeyFrame(player.getTime()));

        if (!animation.isAnimationFinished(player.getTime())) {
            player.setTime(player.getTime() + Gdx.graphics.getDeltaTime());
        }
        else {
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
