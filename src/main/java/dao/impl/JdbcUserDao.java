package dao.impl;

import bean.Role;
import bean.User;
import bean.Vacancy;
import dao.Dao;
import dao.UserDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class JdbcUserDao extends Dao<User> implements UserDao {
    @Override
    public boolean create(User user) {
        try {

            String sqlInsertUser = "INSERT INTO users (name, password,role) values" +
                    " (?, ?, (select id from role where role_name=?))";
            PreparedStatement preparedStatement = getConnection().prepareStatement(sqlInsertUser);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, Role.SEEKER.toString());
            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public User read(User user) {
        User newUser = null;
        String query = "select * from users u left join role r on u.role = r.id where u.id = ? ";
        try {
            PreparedStatement statement = Dao.getConnection().prepareStatement(query);
            statement.setInt(1, user.getId());


            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                newUser = mapUser(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newUser;
    }

    private User mapUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setName(resultSet.getString("name").trim());
        user.setPassword(resultSet.getString("password").trim());
        user.setRole(Role.valueOf(resultSet.getString("role_name").toUpperCase().trim()));
        return user;
    }

    @Override
    public List<User> getAllByVacancyId(Vacancy vacancy) {
        List<User> users = new LinkedList<>();
        try {

            String query = "select u.id , u.name , u .password , r.role_name from users u " +
                    "left join role r on u.role=r.id where u.id in" +
                    " (select user_id from vac_responded where vacancy_id = ?);";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setInt(1, vacancy.getId());

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                users.add(getUserByName(resultSet.getString("name")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public User delete(User user) {
        String query = "DELETE  from users where id = ?";
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setInt(1, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User getUserByName(String name) {
        User user = null;
        String query = "select * from users u left join role r on u.role=r.id where u.name = ?";

        try {
            PreparedStatement statement = getConnection().prepareStatement(query);
            statement.setString(1,name);


            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                   user= mapUser(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> getAllSeekers() {
        List<User> seekers = new LinkedList<>();
        String query = "select * from users where role = (select id from role where role_name = ?)";
        try {
            PreparedStatement statement = getConnection().prepareStatement(query);
            statement.setString(1,"seeker");
            statement.executeQuery();

            ResultSet resultSet =  statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(Role.SEEKER);
                seekers.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seekers;
    }

    @Override
    public List<User> getAllHR() {
        List<User> hrs = new LinkedList<>();
        String query = "select * from users where role = (select id from role where role_name = ?)";
        try {
            PreparedStatement statement = getConnection().prepareStatement(query);
            statement.setString(1,"hr");
            statement.executeQuery();

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(Role.HR);
                hrs.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hrs;
    }
}
