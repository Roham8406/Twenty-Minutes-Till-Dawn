package com.tilldawn.service;

import com.tilldawn.Main;
import com.tilldawn.Model.Avatar;
import com.tilldawn.Model.Response;
import com.tilldawn.Model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserSql {
    private Connection connection;

    public UserSql() {
        String url = "jdbc:mysql://localhost:3306/tmtd";
        String user = "root";
        String password = "HCx_(WQZd@oqX@_t";

        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }


    }

    public User findUser(String username, String password) {

        String query = "SELECT * FROM users WHERE username = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            User user = null;
            if (!resultSet.next()) {
                user = new User(null, Main.getLanguage().UsernameNotFoundError);
                statement.close();
                return user;
            } else if (password != null) {
                if (!resultSet.getString("password").equals(password)) {
                    user = new User(null, Main.getLanguage().WrongPasswordError);
                } else {
                    user = new User(resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getInt("kills"),
                        resultSet.getInt("playtime"),
                        resultSet.getInt("score"),
                        resultSet.getString("avatar"));
                }
            } else {
                user = new User(resultSet.getInt("id"),
                    resultSet.getString("username"),
                    resultSet.getInt("securityQuestion"),
                    resultSet.getString("securityAnswer"));
            }
            resultSet.close();
            statement.close();
            return user;
        } catch (Exception e) {
            return new User(null, e.getMessage());
        }
    }

    public Response changePassowrd(int id, String password) {
        String query = "UPDATE users SET password = ? WHERE id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, password);
            statement.setInt(2, id);
            int rowsAffected = statement.executeUpdate();
            statement.close();
            if (rowsAffected == 0) {
                return new Response(Main.getLanguage().UsernameNotFoundError, false);
            }
            return new Response(Main.getLanguage().PasswordChanged, true);
        } catch (Exception e) {
            return new Response(e.getMessage(), false);
        }
    }


    public static Matcher isPasswordValid(String password) {
        String regex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[@%$#&*()_])[A-Za-z0-9@%$#&*()_]{8,}$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(password);
    }

    public static Matcher isUsernameValid(String username) {
        String regex = "^[A-Za-z0-9_]+$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(username);
    }

    public Response isUsernameAvailable(String username) {
        String query = "SELECT * FROM users WHERE username = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return new Response("", true);
            }
            return new Response(Main.getLanguage().UsernameNotFoundError, false);
        } catch (Exception e) {
            return new Response(e.getMessage(), false);
        }
    }

    public Response registerUser(String username, String password, String securityAnswer, Integer securityQuestion) {
        String query = "INSERT INTO users (username, password, securityQuestion, securityAnswer, avatar) VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setInt(3, securityQuestion);
            statement.setString(4, securityAnswer);
            statement.setString(5, new Avatar().getAvatar());
            int rowsAffected = statement.executeUpdate();
            statement.close();
            if (rowsAffected == 0) {
                return new Response(Main.getLanguage().SomethingWentWrong, false);
            }
            return new Response("", true);
        } catch (Exception e) {
            return new Response("Error: " + e.getMessage(), false);
        }
    }

    public Response updateInfo(String username, String password) {
        if (password == null) {
            String query = "UPDATE users SET username = ? WHERE id = ?";

            try {
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, username);
                statement.setInt(2, Main.getMain().getCurrentUser().getId());
                int rowsAffected = statement.executeUpdate();
                statement.close();
                if (rowsAffected == 0) {
                    return new Response(Main.getLanguage().UsernameNotFoundError, false);
                }
                Main.getMain().getCurrentUser().setUsername(username);
                return new Response(Main.getLanguage().UsernameChanged, true);
            } catch (Exception e) {
                return new Response(e.getMessage(), false);
            }
        } else {
            String query = "UPDATE users SET username = ?, password = ? WHERE id = ?";

            try {
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, username);
                statement.setString(2, password);
                statement.setInt(3, Main.getMain().getCurrentUser().getId());
                int rowsAffected = statement.executeUpdate();
                statement.close();
                if (rowsAffected == 0) {
                    return new Response(Main.getLanguage().UsernameNotFoundError, false);
                }
                Main.getMain().getCurrentUser().setUsername(username);
                Main.getMain().getCurrentUser().setPassword(password);
                return new Response(Main.getLanguage().InfoChanged, true);
            } catch (Exception e) {
                return new Response(e.getMessage(), false);
            }
        }
    }

    public Response deleteAccount() {
        String query = "DELETE FROM users WHERE id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, Main.getMain().getCurrentUser().getId());
            int rowsAffected = statement.executeUpdate();
            statement.close();
            if (rowsAffected == 0) {
                return new Response(Main.getLanguage().UsernameNotFoundError, false);
            }

            return new Response(Main.getLanguage().UserDeleted, true);
        } catch (Exception e) {
            return new Response(e.getMessage(), false);
        }
    }

    public Response updateAvatar(String path) {
        String query = "UPDATE users SET avatar = ? WHERE id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, path);
            statement.setInt(2, Main.getMain().getCurrentUser().getId());
            int rowsAffected = statement.executeUpdate();
            statement.close();
            if (rowsAffected == 0) {
                return new Response(Main.getLanguage().UsernameNotFoundError, false);
            }
            return new Response(Main.getLanguage().AvatarChanged, true);
        } catch (Exception e) {
            return new Response(e.getMessage(), false);
        }
    }


    public ArrayList<User> sortScoreboard(String scoreName) {
        String query = "SELECT * FROM `users` ORDER BY `" + scoreName + "` DESC LIMIT 10";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            ArrayList<User> users = new ArrayList<>();

            while (resultSet.next()) {
                users.add(new User(0,
                    resultSet.getString("username"),
                    resultSet.getInt("kills"),
                    resultSet.getInt("playTime"),
                    resultSet.getInt("score"), null));
            }

            return users;
        } catch (Exception e) {
            return null;
        }
    }

    public void updateStats(User user) {
        String query = "UPDATE users SET score = ?, kills = ?, playtime = ?  WHERE id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, user.getScore());
            statement.setInt(2, user.getKills());
            statement.setInt(3, user.getPlaytime());
            statement.setInt(4, user.getId());
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            int a = 6;
        }
    }
}
