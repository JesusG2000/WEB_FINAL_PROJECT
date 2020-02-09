package dao.impl;

import bean.Role;
import bean.User;
import bean.Vacancy;
import dao.UserDao;
import dao.impl.util.JdbcMapper;
import dao.pool.ConnectionPool;
import exception.ConnectionPoolException;
import exception.JdbcDaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class JdbcUserDao implements UserDao {
    private ConnectionPool connectionPool = ConnectionPool.getInstance();
    private Connection connection;
    private JdbcMapper mapper;

    public JdbcUserDao() {
        mapper = new JdbcMapper();
        try {
            connection = connectionPool.takeConnection();
        } catch (ConnectionPoolException e) {

        }
    }

    @Override
    public User getUserByName(String name) throws JdbcDaoException {
        User user = null;
        if (connection != null) {
            String query = "select * from users u left join role r on u.role=r.id where u.name = ?";
            PreparedStatement statement = null;
            try {
                statement = connection.prepareStatement(query);
                statement.setString(1, name);

                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    user = mapper.userMap(resultSet);
                }
            } catch (SQLException e) {
                throw new JdbcDaoException("Prepared statement error", e);
            } finally {
                connectionPool.closeConnection(connection, statement);
            }
        }
        return user;
    }

    @Override
    public List<User> getAllSeekers() throws JdbcDaoException {
        List<User> seekers = null;
        if (connection != null) {
            seekers = new LinkedList<>();
            String query = "select * from users where role = (select id from role where role_name = ?)";
            PreparedStatement statement = null;
            try {
                statement = connection.prepareStatement(query);
                statement.setString(1, "seeker");
                statement.executeQuery();

                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    User user = new User();
                    user.setId(resultSet.getInt("id"));
                    user.setName(resultSet.getString("name"));
                    user.setPassword(resultSet.getString("password"));
                    user.setRole(Role.SEEKER);
                    seekers.add(user);
                }
            } catch (SQLException e) {
                throw new JdbcDaoException("Prepared statement error", e);
            } finally {
                connectionPool.closeConnection(connection, statement);
            }
        }
        return seekers;
    }

    @Override
    public List<User> getAllHR() throws JdbcDaoException {
        List<User> hrs = null;
        if (connection != null) {
            hrs = new LinkedList<>();
            String query = "select * from users where role = (select id from role where role_name = ?)";
            PreparedStatement statement = null;
            try {
                statement = connection.prepareStatement(query);
                statement.setString(1, "hr");
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
                throw new JdbcDaoException("Prepared statement error", e);
            } finally {
                connectionPool.closeConnection(connection, statement);
            }
        }
        return hrs;
    }

    @Override
    public List<User> getAllByVacancy(Vacancy vacancy) throws JdbcDaoException {
        List<User> users = null;
        if (connection != null) {
            users = new LinkedList<>();
            String query = "select u.id , u.name , u .password , r.role_name from users u " +
                    "left join role r on u.role=r.id where u.id in" +
                    " (select user_id from vac_responded where vacancy_id = ?);";
            PreparedStatement statement = null;
            try {
                statement = connection.prepareStatement(query);
                statement.setInt(1, vacancy.getId());

                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    users.add(getUserByName(resultSet.getString("name")));
                }

            } catch (SQLException e) {
                throw new JdbcDaoException("Prepared statement error", e);
            } finally {
                connectionPool.closeConnection(connection, statement);
            }
        }
        return users;
    }

    @Override
    public boolean isRegistered(User user) throws JdbcDaoException {
        if (connection != null) {
            String query = "select * from users where name=?";
            PreparedStatement statement = null;
            try {
                statement = connection.prepareStatement(query);
                statement.setString(1, user.getName());

                ResultSet resultSet = statement.executeQuery();
                resultSet.last();
                return resultSet.getRow() != 0;

            } catch (SQLException e) {
                throw new JdbcDaoException("Prepared statement error", e);
            } finally {
                connectionPool.closeConnection(connection, statement);
            }
        }
        return false;
    }

    @Override
    public boolean isExist(User user) throws JdbcDaoException {
        if (connection != null) {
            String query = "select * from users where name = ? and password = ? ";
            PreparedStatement statement = null;
            try {
                statement = connection.prepareStatement(query);
                statement.setString(1, user.getName());
                statement.setString(2, user.getPassword());

                ResultSet resultSet = statement.executeQuery();
                resultSet.last();
                return resultSet.getRow() != 0;
            } catch (SQLException e) {
                throw new JdbcDaoException("Prepared statement error", e);
            } finally {
                connectionPool.closeConnection(connection, statement);
            }
        }
        return false;
    }

    @Override
    public void create(User user) throws JdbcDaoException {
        if (connection != null) {
            String sqlInsertUser = "INSERT INTO users (name, password,role) values" +
                    " (?, ?, (select id from role where role_name=?))";
            PreparedStatement statement = null;
            try {
                statement = connection.prepareStatement(sqlInsertUser);

                statement.setString(1, user.getName());
                statement.setString(2, user.getPassword());
                statement.setString(3, Role.SEEKER.toString());
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new JdbcDaoException("Prepared statement error", e);
            } finally {
                connectionPool.closeConnection(connection, statement);
            }
        }

    }

    @Override
    public User readById(int id) throws JdbcDaoException {
        User newUser = null;
        if (connection != null) {
            String query = "select * from users u left join role r on u.role = r.id where u.id = ? ";
            PreparedStatement statement = null;
            try {
                statement = connection.prepareStatement(query);
                statement.setInt(1, id);

                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    newUser = mapper.userMap(resultSet);
                }
            } catch (SQLException e) {
                throw new JdbcDaoException("Prepared statement error", e);
            } finally {
                connectionPool.closeConnection(connection, statement);
            }
        }
        return newUser;
    }

    @Override
    public User update(User user) throws JdbcDaoException {
        return null;
    }

    @Override
    public void deleteById(int id) throws JdbcDaoException {
        if (connection != null) {
            String query = "DELETE  from users where id = ?";
            PreparedStatement statement = null;
            try {
                statement = connection.prepareStatement(query);
                statement.setInt(1, id);
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new JdbcDaoException("Prepared statement error", e);
            } finally {
                connectionPool.closeConnection(connection, statement);
            }
        }
    }
}
