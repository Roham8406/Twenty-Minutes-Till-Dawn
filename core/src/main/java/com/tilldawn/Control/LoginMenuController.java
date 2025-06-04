package com.tilldawn.Control;

import com.tilldawn.Main;
import com.tilldawn.Model.GameAssetManager;
import com.tilldawn.Model.User;
import com.tilldawn.View.ForgotPasswordMenuView;
import com.tilldawn.View.LoginMenuView;
import com.tilldawn.View.MainMenuView;
import com.tilldawn.View.RegisterMenuView;

public class LoginMenuController extends MenuController {
    private LoginMenuView view;

    public void setView(LoginMenuView view) {
        this.view = view;
    }

    public void handleMainMenuButtons() {
        addClickSoundToButtons(view.getStage().getRoot());
        if (view != null) {
            if (view.getLoginButton().isChecked()) {
                String username = view.getUsernameField().getText();
                String password = view.getPasswordField().getText();
                if (username.isEmpty() || password.isEmpty()) {
                    view.alert(Main.getLanguage().EmptyFieldError, 5);
                    view.getLoginButton().setChecked(false);
                    return;
                }
                User user = Main.getMain().getUserSql().findUser(username, password);
                if (user.getUsername() == null) {
                    view.alert(user.getAlert(), 5);
                    view.getLoginButton().setChecked(false);
                    return;
                }
                Main.getMain().setCurrentUser(user);
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new MainMenuView(new MainMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
            if (view.getForgotPassword().isChecked()) {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new ForgotPasswordMenuView(new ForgotPasswordMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
            if (view.getRegister().isChecked()) {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new RegisterMenuView(new RegisterMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
            if (view.getPlayAsGuest().isChecked()) {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new MainMenuView(new MainMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
        }
    }
}
