package by.epam.dao.impl;

import by.epam.bean.Vacancy;
import by.epam.dao.VacancyDao;
import by.epam.dao.impl.util.JdbcMapper;
import by.epam.dao.pool.ConnectionPool;
import by.epam.exception.ConnectionPoolException;
import by.epam.exception.JdbcDaoException;
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

    private JdbcMapper mapper = new JdbcMapper();


    @Override
    public List<Vacancy> getAllVacancy() throws JdbcDaoException {
        List<Vacancy> vacancies = null;


        String query = "select * from vacancy";
        Connection connection = null;
        ResultSet resultSet = null;
        try {
            connection = connectionPool.takeConnection();
            vacancies = new LinkedList<>();
            resultSet = connection.createStatement().executeQuery(query);
            while (resultSet.next()) {
                vacancies.add(mapper.vacancyMap(resultSet));
            }
        } catch (SQLException | ConnectionPoolException e) {
            log.error(e);
            throw new JdbcDaoException("Prepared statement error", e);
        } finally {
            connectionPool.closeConnection(connection, resultSet);
        }
        return vacancies;
    }

    @Override
    public boolean isExist(Vacancy vacancy) throws JdbcDaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;


        String query = "select * from vacancy where name = ? and description = ? ";
        try {
            connection = connectionPool.takeConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, vacancy.getName());
            statement.setString(2, vacancy.getDescription());

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
    public void create(Vacancy vacancy) throws JdbcDaoException {
        if (!isExist(vacancy)) {
            String sqlInsertUser = "INSERT INTO vacancy (name, description) values (?, ?)";
            PreparedStatement statement = null;
            Connection connection = null;
            try {
                connection = connectionPool.takeConnection();
                statement = connection.prepareStatement(sqlInsertUser);

                statement.setString(1, vacancy.getName());
                statement.setString(2, vacancy.getDescription());
                statement.executeUpdate();

            } catch (SQLException | ConnectionPoolException e) {
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

        String sqlInsertUser = "SELECT * from vacancy where id = ?";
        PreparedStatement statement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        try {
            connection = connectionPool.takeConnection();
            statement = connection.prepareStatement(sqlInsertUser);
            statement.setString(1, String.valueOf(id));

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                newVacancy = mapper.vacancyMap(resultSet);
            }
        } catch (SQLException | ConnectionPoolException e) {
            log.error(e);
            throw new JdbcDaoException("Prepared statement error", e);
        } finally {
            connectionPool.closeConnection(connection, statement, resultSet);
        }
        return newVacancy;
    }

    @Override
    public Vacancy update(Vacancy vacancy) throws JdbcDaoException {

        String sqlInsertUser = "UPDATE vacancy set name = ? , description = ? where id = ? ";
        PreparedStatement statement = null;

        Connection connection = null;
        try {
            connection = connectionPool.takeConnection();
            statement = connection.prepareStatement(sqlInsertUser);
            statement.setString(1, vacancy.getName());
            statement.setString(2, vacancy.getDescription());
            statement.setInt(3, vacancy.getId());
            statement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            log.error(e);
            throw new JdbcDaoException("Prepared statement error", e);
        } finally {
            connectionPool.closeConnection(connection, statement);
        }

        return vacancy;
    }

    @Override
    public void deleteById(int id) throws JdbcDaoException {

        String sqlInsertUser = "DELETE  from vacancy where id = ?";
        PreparedStatement statement = null;
        Connection connection = null;
        try {
            connection = connectionPool.takeConnection();
            statement = connection.prepareStatement(sqlInsertUser);
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
