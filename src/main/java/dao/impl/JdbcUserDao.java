package dao.impl;

import bean.Role;
import bean.User;
import bean.Vacancy;
import dao.UserDao;
import dao.impl.util.JdbcMapper;
import dao.pool.ConnectionPool;
import exception.ConnectionPoolException;
import exception.JdbcDaoException;
import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class JdbcUserDao implements UserDao {
    private static Logger log = Logger.getLogger(JdbcUserDao.class);
    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    private JdbcMapper mapper = new JdbcMapper();


    @Override
    public User getUserByName(String name) throws JdbcDaoException {
        User user = null;
        Connection connection = null;
        PreparedStatement statement = null;

        String query = "select * from users u left join role r on u.role=r.id where u.name = ?";
        try {
            connection = connectionPool.takeConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, name);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user = mapper.userMap(resultSet);
            }
        } catch (SQLException | ConnectionPoolException e) {
            log.error(e);
            throw new JdbcDaoException("Prepared statement error", e);
        } finally {
            connectionPool.closeConnection(connection, statement);
        }
        return user;
    }

    @Override
    public List<User> getAllSeekers() throws JdbcDaoException {
        List<User> seekers;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        String query = "select * from users where role = (select id from role where role_name = ?)";

        try {
            connection = connectionPool.takeConnection();
            seekers = new LinkedList<>();

            statement = connection.prepareStatement(query);
            statement.setString(1, "seeker");

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(Role.SEEKER);
                seekers.add(user);
            }
        } catch (SQLException | ConnectionPoolException e) {
            log.error(e);
            throw new JdbcDaoException("Prepared statement error", e);
        } finally {
            connectionPool.closeConnection(connection, statement, resultSet);
        }

        return seekers;
    }

    @Override
    public List<User> getAllHR() throws JdbcDaoException {
        List<User> hrs = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        String query = "select * from users where role = (select id from role where role_name = ?)";
        try {
            connection = connectionPool.takeConnection();
            hrs = new LinkedList<>();

            statement = connection.prepareStatement(query);
            statement.setString(1, "hr");
            statement.executeQuery();

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(Role.HR);
                hrs.add(user);
            }
        } catch (SQLException | ConnectionPoolException e) {
            log.error(e);
            throw new JdbcDaoException("Prepared statement error", e);
        } finally {
            connectionPool.closeConnection(connection, statement, resultSet);
        }
        return hrs;
    }

    @Override
    public List<User> getAllByVacancy(Vacancy vacancy) throws JdbcDaoException {
        List<User> users = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;

        String query = "select u.id , u.name , u .password , r.role_name from users u " +
                "left join role r on u.role=r.id where u.id in" +
                " (select user_id from vac_responded where vacancy_id = ?);";
        try {
            users = new LinkedList<>();
            connection = connectionPool.takeConnection();

            statement = connection.prepareStatement(query);
            statement.setInt(1, vacancy.getId());

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                users.add(getUserByName(resultSet.getString("name")));
            }

        } catch (SQLException | ConnectionPoolException e) {
            log.error(e);
            throw new JdbcDaoException("Prepared statement error", e);
        } finally {
            connectionPool.closeConnection(connection, statement, resultSet);
        }
        return users;
    }

    @Override
    public boolean isRegistered(User user) throws JdbcDaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;

        String query = "select * from users where name=?";
        try {
            connection = connectionPool.takeConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, user.getName());

            resultSet = statement.executeQuery();
            resultSet.last();
            return resultSet.getRow() != 0;

        } catch (SQLException | ConnectionPoolException e) {
            log.error(e);
            throw new JdbcDaoException("Prepared statement error", e);
        } finally {
            connectionPool.closeConnection(connection, statement, resultSet);
        }
    }

    @Override
    public boolean isExist(User user) throws JdbcDaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        String hashPassword = null;

        String query = "select * from users where name = ?  ";
        try {
            connection = connectionPool.takeConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, user.getName());

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                hashPassword = resultSet.getString("password");
            }
            resultSet.last();
            return resultSet.getRow() != 0 && BCrypt.checkpw(user.getPassword(), hashPassword);
        } catch (SQLException | ConnectionPoolException e) {
            log.error(e);
            throw new JdbcDaoException("Prepared statement error", e);
        } finally {
            connectionPool.closeConnection(connection, statement, resultSet);
        }
    }

    @Override
    public void create(User user) throws JdbcDaoException {
        PreparedStatement statement = null;
        Connection connection = null;

        String sqlInsertUser = "INSERT INTO users (name, password,role) values" +
                " (?, ?, (select id from role where role_name=?))";
        try {
            connection = connectionPool.takeConnection();
            statement = connection.prepareStatement(sqlInsertUser);

            statement.setString(1, user.getName());
            statement.setString(2, BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
            statement.setString(3, Role.SEEKER.toString());
            statement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            log.error(e);
            throw new JdbcDaoException("Prepared statement error", e);
        } finally {
            connectionPool.closeConnection(connection, statement);
        }
    }

    @Override
    public User readById(int id) throws JdbcDaoException {
        User newUser = null;

        String query = "select * from users u left join role r on u.role = r.id where u.id = ? ";
        PreparedStatement statement = null;
        Connection connection = null;
        ResultSet resultSet = null;

        try {
            connection = connectionPool.takeConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                newUser = mapper.userMap(resultSet);
            }
        } catch (SQLException | ConnectionPoolException e) {
            log.error(e);
            throw new JdbcDaoException("Prepared statement error", e);
        } finally {
            connectionPool.closeConnection(connection, statement, resultSet);
        }
        return newUser;
    }

    @Override
    public User update(User user) throws JdbcDaoException {
        return null;
    }

    @Override
    public void deleteById(int id) throws JdbcDaoException {

            String query = "DELETE  from users where id = ?";
            PreparedStatement statement = null;
            Connection connection = null;
            try {
                connection = connectionPool.takeConnection();
                statement = connection.prepareStatement(query);
                statement.setInt(1, id);
                statement.executeUpdate();
            } catch (SQLException | ConnectionPoolException e) {
                log.error(e);
                throw new JdbcDaoException("Prepared statement error", e);
            } finally {
                connectionPool.closeConnection(connection, statement);
            }
    }
}
