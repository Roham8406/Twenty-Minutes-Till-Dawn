package com.tilldawn.Control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.tilldawn.Model.Sfx;

public abstract class MenuController {
    private Sound click;

    MenuController() {
        click = Gdx.audio.newSound(Gdx.files.internal(Sfx.Click.getPath()));
    }
    public void addClickSoundToButtons(Group group) {
        for (Actor actor : group.getChildren()) {
            if (actor instanceof TextButton ||
                actor instanceof SelectBox ||
                actor instanceof Slider) {
                actor.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        click.play();
                    }
                });
            }
            if (actor instanceof Group) {
                addClickSoundToButtons((Group) actor);
            }
        }
    }
}
