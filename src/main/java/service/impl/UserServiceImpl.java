package service.impl;

import bean.User;
import dao.Dao;
import service.UserService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserServiceImpl implements UserService {
    @Override
    public boolean isRegistered(User user) {
        String query = "select * from users where name=?";

        try {
            PreparedStatement statement = Dao.getConnection().prepareStatement(query);
            statement.setString(1 , user.getName());

            ResultSet resultSet = statement.executeQuery();
            resultSet.last();
            return resultSet.getRow()!=0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean isExist(User user) {
        String query = "select * from users where name = ? and password = ? ";
        try {
            PreparedStatement statement = Dao.getConnection().prepareStatement(query);
            statement.setString(1, user.getName());
            statement.setString(2, user.getPassword());

            ResultSet resultSet = statement.executeQuery();
            resultSet.last();
            return resultSet.getRow() != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
