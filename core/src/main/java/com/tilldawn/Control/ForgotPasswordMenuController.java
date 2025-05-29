package com.tilldawn.Control;

import com.tilldawn.Main;
import com.tilldawn.Model.GameAssetManager;
import com.tilldawn.Model.Response;
import com.tilldawn.Model.User;
import com.tilldawn.View.ForgotPasswordMenuView;
import com.tilldawn.View.LoginMenuView;
import com.tilldawn.View.MainMenuView;
import com.tilldawn.service.UserSql;

public class ForgotPasswordMenuController extends MenuController {
    private ForgotPasswordMenuView view;
    User user;

    public void setView(ForgotPasswordMenuView view) {
        this.view = view;
    }

    public void handleMainMenuButtons() {
        addClickSoundToButtons(view.getStage().getRoot());
        if (view != null) {
            switch (view.getState()) {
                case 0: {
                    if (view.getFindUsername().isChecked()) {
                        if (view.getUsernameField().getText().isEmpty()) {
                            view.alert(Main.getLanguage().NoUsernameError, 5);
                            view.getFindUsername().setChecked(false);
                            return;
                        }
                        String username = view.getUsernameField().getText();
                        user = Main.getMain().getUserSql().findUser(username, null);
                        if (user.getUsername() == null) {
                            view.alert(user.getAlert(), 5);
                            view.getFindUsername().setChecked(false);
                            return;
                        }
                        this.view = view.setState(1);
                        view.setSecurityQuestion(user.getSecurityQuestion());
                    }
                } break;
                case 1: {
                    if (view.getAuthorize().isChecked()) {
                        if (view.getSecurityAnswerField().getText().isEmpty()) {
                            view.alert(Main.getLanguage().NoAnswerError, 5);
                            view.getAuthorize().setChecked(false);
                            return;
                        }
                        String answer = view.getSecurityAnswerField().getText();
                        if (!user.getSecurityAnswer().equals(answer)) {
                            view.alert(Main.getLanguage().IncorrectAnswerError, 5);
                            view.getAuthorize().setChecked(false);
                            return;
                        }
                        view.setState(2);
                    }
                } break;
                case 2: {
                    if (view.getChangePassword().isChecked()) {
                        if (view.getNewPasswordField().getText().isEmpty()) {
                            view.alert(Main.getLanguage().NoNewPasswordError, 5);
                            view.getChangePassword().setChecked(false);
                            return;
                        }
                        if (!UserSql.isPasswordValid(view.getNewPasswordField().getText()).matches()) {
                            view.alert(Main.getLanguage().EasyPasswordError, 5);
                            view.getChangePassword().setChecked(false);
                            return;
                        }
                        Response response = Main.getMain().getUserSql().changePassowrd(
                            user.getId(), view.getNewPasswordField().getText());
                        if (!response.isSuccess()) {
                            view.alert(response.getMessage(), 5);
                            view.getChangePassword().setChecked(false);
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
