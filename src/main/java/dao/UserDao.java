package dao;

import bean.User;

import java.util.List;

public interface UserDao {
    User getUserByName(String name);
    List<User> getAllSeekers();
    List<User> getAllHR();
    boolean checkNameDuplicate(String name);
    boolean isLogin(String username, String password);
}
