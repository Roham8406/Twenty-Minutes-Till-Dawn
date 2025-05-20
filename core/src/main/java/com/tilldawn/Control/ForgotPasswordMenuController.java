package com.tilldawn.Control;

import com.tilldawn.Main;
import com.tilldawn.Model.GameAssetManager;
import com.tilldawn.Model.Response;
import com.tilldawn.Model.User;
import com.tilldawn.View.ForgotPasswordMenuView;
import com.tilldawn.View.LoginMenuView;
import com.tilldawn.View.MainMenuView;
import com.tilldawn.service.UserSql;

public class ForgotPasswordMenuController {
    private ForgotPasswordMenuView view;
    User user;

    public void setView(ForgotPasswordMenuView view) {
        this.view = view;
    }

    public void handleMainMenuButtons() {
        if (view != null) {
            switch (view.getState()) {
                case 0: {
                    if (view.getFindUsername().isChecked()) {
                        if (view.getUsernameField().getText().isEmpty()) {
                            view.alert("Please enter your username.", 5);
                            return;
                        }
                        String username = view.getUsernameField().getText();
                        user = Main.getMain().getUserSql().findUser(username, null);
                        if (user.getUsername() == null) {
                            view.alert(user.getAlert(), 5);
                            return;
                        }
                        this.view = view.setState(1);
                        view.setSecurityQuestion(user.getSecurityQuestion());
                    }
                } break;
                case 1: {
                    if (view.getAuthorize().isChecked()) {
                        if (view.getSecurityAnswerField().getText().isEmpty()) {
                            view.alert("Please answer the question.", 5);
                            return;
                        }
                        String answer = view.getSecurityAnswerField().getText();
                        if (!user.getSecurityAnswer().equals(answer)) {
                            view.alert("The answer you entered is incorrect.", 5);
                            return;
                        }
                        view.setState(2);
                    }
                } break;
                case 2: {
                    if (view.getChangePassword().isChecked()) {
                        if (!UserSql.isPasswordValid(view.getNewPasswordField().getText()).matches()) {
                            view.alert("Password too easy.", 5);
                            return;
                        }
                        Response response = Main.getMain().getUserSql().changePassowrd(
                            user.getId(), view.getNewPasswordField().getText());
                        if (!response.isSuccess()) {
                            view.alert(response.getMessage(), 5);
                            return;
                        }
                        Main.getMain().getScreen().dispose();
                        Main.getMain().setScreen(new LoginMenuView(new LoginMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
                    }
                }
            }
        }
    }
}
