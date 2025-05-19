package com.tilldawn.Control;

import com.tilldawn.Main;
import com.tilldawn.Model.GameAssetManager;
import com.tilldawn.Model.User;
import com.tilldawn.View.LoginMenuView;
import com.tilldawn.View.MainMenuView;
import com.tilldawn.View.TalentMenuView;

public class LoginMenuController {
    private LoginMenuView view;

    public void setView(LoginMenuView view) {
        this.view = view;
    }

    public void handleMainMenuButtons() {
        if (view != null) {
            if (view.getLoginButton().isChecked()) {
                String username = view.getUsernameField().getText();
                String password = view.getPasswordField().getText();
                if  (username.equals("") || password.equals("")) {
                    view.alert("The fields are essential");
                    return;
                }
                User user = Main.getMain().getUserSql().findUser(username, password);
                if (user.getUsername() == null)  {
                    view.alert(user.getAlert());
                    return;
                }
                Main.getMain().setCurrentUser(user);
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new MainMenuView(new MainMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
        }
    }
}
