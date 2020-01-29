package dao;

import bean.User;

import java.util.List;

public interface UserDao {
    User getUserByName(String name);
    List<User> getAllSeekers();
    List<User> getAllHR();

}
