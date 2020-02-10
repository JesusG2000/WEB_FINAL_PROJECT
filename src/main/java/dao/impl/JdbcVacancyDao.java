package dao.impl;

import bean.Vacancy;
import dao.VacancyDao;
import dao.impl.util.JdbcMapper;
import dao.pool.ConnectionPool;
import exception.ConnectionPoolException;
import exception.JdbcDaoException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class JdbcVacancyDao implements VacancyDao {
    private static Logger log = Logger.getLogger(JdbcVacancyDao.class);
    private ConnectionPool connectionPool = ConnectionPool.getInstance();
    private Connection connection;
    private JdbcMapper mapper;

    public JdbcVacancyDao() {
        mapper = new JdbcMapper();
        try {
            connection = connectionPool.takeConnection();
        } catch (ConnectionPoolException e) {
log.error(e);
        }
    }

    @Override
    public List<Vacancy> getAllVacancy() throws JdbcDaoException {
        List<Vacancy> vacancies = null;
        if (connection != null) {
            vacancies = new LinkedList<>();
            String query = "select * from vacancy";
            try {
                ResultSet resultSet = connection.createStatement().executeQuery(query);
                while (resultSet.next()) {
                    vacancies.add(mapper.vacancyMap(resultSet));
                }
            } catch (SQLException e) {
                log.error(e);
                throw new JdbcDaoException("Prepared statement error", e);
            } finally {
                connectionPool.closeConnection(connection);
            }
        }
        return vacancies;
    }

    @Override
    public void create(Vacancy vacancy) throws JdbcDaoException {
        if (connection != null) {
            String sqlInsertUser = "INSERT INTO vacancy (name, description) values (?, ?)";
            PreparedStatement statement = null;
            try {
                statement = connection.prepareStatement(sqlInsertUser);

                statement.setString(1, vacancy.getName());
                statement.setString(2, vacancy.getDescription());
                statement.executeUpdate();

            } catch (SQLException e) {
                log.error(e);
                throw new JdbcDaoException("Prepared statement error", e);
            } finally {
                connectionPool.closeConnection(connection, statement);
            }
        }

    }

    @Override
    public Vacancy readById(int id) throws JdbcDaoException {
        Vacancy newVacancy = null;
        if (connection != null) {
            String sqlInsertUser = "SELECT * from vacancy where id = ?";
            PreparedStatement statement = null;
            try {
                statement = connection.prepareStatement(sqlInsertUser);
                statement.setString(1, String.valueOf(id));

                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    newVacancy = mapper.vacancyMap(resultSet);
                }
            } catch (SQLException e) {
                log.error(e);
                throw new JdbcDaoException("Prepared statement error", e);
            } finally {
                connectionPool.closeConnection(connection, statement);
            }
        }
        return newVacancy;
    }

    @Override
    public Vacancy update(Vacancy vacancy) throws JdbcDaoException {
        if (connection != null) {
            String sqlInsertUser = "UPDATE vacancy set name = ? , description = ? where id = ? ";
            PreparedStatement statement = null;

            try {
                statement = connection.prepareStatement(sqlInsertUser);
                statement.setString(1, vacancy.getName());
                statement.setString(2, vacancy.getDescription());
                statement.setInt(3, vacancy.getId());
                statement.executeUpdate();
            } catch (SQLException e) {
                log.error(e);
                throw new JdbcDaoException("Prepared statement error", e);
            } finally {
                connectionPool.closeConnection(connection, statement);
            }
        }
        return vacancy;
    }

    @Override
    public void deleteById(int id) throws JdbcDaoException {
        if (connection != null) {
            String sqlInsertUser = "DELETE  from vacancy where id = ?";
            PreparedStatement statement = null;
            try {
                statement = connection.prepareStatement(sqlInsertUser);
                statement.setInt(1, id);
                statement.executeUpdate();
            } catch (SQLException e) {
                log.error(e);
                throw new JdbcDaoException("Prepared statement error", e);
            } finally {
                connectionPool.closeConnection(connection, statement);
            }
        }
    }
}
