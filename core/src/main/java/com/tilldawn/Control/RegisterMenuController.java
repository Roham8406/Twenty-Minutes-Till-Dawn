package com.tilldawn.Control;

import com.tilldawn.Main;
import com.tilldawn.Model.GameAssetManager;
import com.tilldawn.Model.Response;
import com.tilldawn.View.LoginMenuView;
import com.tilldawn.View.MainMenuView;
import com.tilldawn.View.RegisterMenuView;
import com.tilldawn.service.UserSql;

public class RegisterMenuController extends MenuController {
    private RegisterMenuView view;

    public void setView(RegisterMenuView view) {
        this.view = view;
    }

    public void handleMainMenuButtons() {
        addClickSoundToButtons(view.getStage().getRoot());
        if (view != null) {
            if (view.getRegister().isChecked()) {
                String username = view.getUsernameField().getText();
                String password = view.getPasswordField().getText();
                String securityAnswer = view.getSecurityAnswer().getText();
                Integer securityQuestion = view.getSecurityQuestion().getSelectedIndex();
                if (username.isEmpty() || password.isEmpty() || securityAnswer.isEmpty()) {
                    view.alert("The fields are essential", 5);
                    view.getRegister().setChecked(false);
                    return;
                }
                if (!UserSql.isUsernameValid(username).matches()) {
                    view.alert("The username must contain only a-zA-z0-9_", 5);
                    view.getRegister().setChecked(false);
                    return;
                }
                if (!UserSql.isPasswordValid(password).matches()) {
                    view.alert("The password is too easy", 5);
                    view.getRegister().setChecked(false);
                    return;
                }
                Response response = Main.getMain().getUserSql().isUsernameAvailable(username);
                if (!response.isSuccess()) {
                    view.alert(response.getMessage(), 5);
                    view.getRegister().setChecked(false);
                    return;
                }
                response = Main.getMain().getUserSql().registerUser(username, password, securityAnswer, securityQuestion);
                if (!response.isSuccess()) {
                    view.alert(response.getMessage(), 5);
                    view.getRegister().setChecked(false);
                    return;
                }
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new LoginMenuView(new LoginMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
            if (view.getPlayAsGuest().isChecked()) {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new MainMenuView(new MainMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
        }
    }
}
