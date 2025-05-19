package com.tilldawn.service;

import com.tilldawn.Model.User;

import java.sql.*;

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
                user = new User(null, "Username not found.");
                statement.close();
                return user;
            } else if (password != null) {
                if (!resultSet.getString("password").equals(password)) {
                    user = new User(null, "Password doesn't match");
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
                    resultSet.getInt("kills"),
                    resultSet.getInt("playtime"),
                    resultSet.getInt("score"),
                    resultSet.getString("avatar"),
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
}
