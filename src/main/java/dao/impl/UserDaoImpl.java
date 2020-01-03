package dao.impl;

import bean.User;
import dao.Dao;
import dao.UserDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UserDaoImpl extends Dao<User> implements UserDao {


    @Override
    public User create(String name, String password) {
        return new User(name,password);
    }

    @Override
    public User read(int index) {
        return null;
    }

    @Override
    public User update(int index) {
        return null;
    }

    @Override
    public User delete(int index) {
        return null;
    }
}
