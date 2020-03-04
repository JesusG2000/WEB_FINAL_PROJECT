package by.epam.dao.impl;

import by.epam.bean.User;
import by.epam.bean.VacResponded;
import by.epam.dao.VacRespondedDao;
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

public class JdbcVacRespondedDao implements VacRespondedDao {
    private static Logger log = Logger.getLogger(JdbcVacRespondedDao.class);
    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    private JdbcMapper mapper = new JdbcMapper();


    @Override
    public List<VacResponded> getAllVacRespondedBySeeker(User user) throws JdbcDaoException {
        List<VacResponded> vacRespondedList = null;


        String query = "select * from vac_responded where user_id = ?";
        PreparedStatement statement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        try {
            vacRespondedList = new LinkedList<>();
            connection = connectionPool.takeConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, user.getId());

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                vacRespondedList.add(mapper.vacRespondedMap(resultSet));
            }
        } catch (SQLException | ConnectionPoolException e) {
            log.error(e);
            throw new JdbcDaoException("Prepared statement error", e);
        } finally {
            connectionPool.closeConnection(connection, statement, resultSet);

        }
        return vacRespondedList;
    }

    @Override
    public void deleteAllVacRespondedBySeeker(User user) throws JdbcDaoException {
        List<VacResponded> vacRespondedList = getAllVacRespondedBySeeker(user);
        for (VacResponded vacResponded : vacRespondedList) {
            deleteById(vacResponded.getId());
        }
    }

    @Override
    public boolean isExist(VacResponded vacResponded) throws JdbcDaoException {

        String query = "select * from vac_responded where user_id = ? and vacancy_id = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = connectionPool.takeConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, vacResponded.getUserId());
            statement.setInt(2, vacResponded.getVacancyId());

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
    public void create(VacResponded vacResponded) throws JdbcDaoException {

        if (!isExist(vacResponded)) {
            String query = "insert into vac_responded (user_id,vacancy_id) values (? , ?)";
            PreparedStatement statement = null;
            Connection connection = null;
            try {
                connection = connectionPool.takeConnection();
                statement = connection.prepareStatement(query);
                statement.setInt(1, vacResponded.getUserId());
                statement.setInt(2, vacResponded.getVacancyId());
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
    public VacResponded readById(int id) throws JdbcDaoException {
        VacResponded newVacResponded = null;

        String query = "select * from vac_responded where vacancy_id = ? and user_id = ? ";
        PreparedStatement statement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        try {
            connection = connectionPool.takeConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.setInt(2, id);

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                newVacResponded = mapper.vacRespondedMap(resultSet);
            }
        } catch (SQLException | ConnectionPoolException e) {
            log.error(e);
            throw new JdbcDaoException("Prepared statement error", e);
        } finally {
            connectionPool.closeConnection(connection, statement, resultSet);
        }

        return newVacResponded;
    }

    @Override
    public VacResponded update(VacResponded vacResponded) throws JdbcDaoException {
        return null;
    }

    @Override
    public void deleteById(int id) throws JdbcDaoException {

        String query = "DELETE  from vac_responded where id = ?";
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
