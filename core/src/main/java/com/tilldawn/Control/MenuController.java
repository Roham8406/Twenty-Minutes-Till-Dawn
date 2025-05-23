package com.tilldawn.Control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.tilldawn.Main;
import com.tilldawn.Model.Sfx;

public abstract class MenuController {

    MenuController() {
    }
    public void addClickSoundToButtons(Group group) {
        for (Actor actor : group.getChildren()) {
            if (actor instanceof TextButton ||
                actor instanceof SelectBox ||
                actor instanceof Slider) {
                actor.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        if (Main.getMain().isSfx()) Sfx.Click.play();
                    }
                });
            }
            if (actor instanceof Group) {
                addClickSoundToButtons((Group) actor);
            }
        }
    }
}
