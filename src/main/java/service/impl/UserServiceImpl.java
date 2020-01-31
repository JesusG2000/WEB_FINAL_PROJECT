package service.impl;

import dao.Dao;
import service.UserService;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserServiceImpl implements UserService {
    @Override
    public boolean isRegistered(String name) {
        String query = "select name from users";

        try {
            ResultSet resultSet = Dao.getConnection().createStatement().executeQuery(query);
            while (resultSet.next()) {
                if (resultSet.getString(1).equals(name)) {
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean isExist(String username, String password) {
        String query = "select name , password from users";

        try {
            ResultSet resultSet = Dao.getConnection().createStatement().executeQuery(query);
            while (resultSet.next()) {
                if (resultSet.getString(1).equals(username) && resultSet.getString(2).equals(password)) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
