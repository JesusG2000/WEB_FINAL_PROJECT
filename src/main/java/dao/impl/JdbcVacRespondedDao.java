package dao.impl;

import bean.User;
import bean.VacResponded;
import dao.VacRespondedDao;
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

public class JdbcVacRespondedDao implements VacRespondedDao {
    private static Logger log = Logger.getLogger(JdbcVacRespondedDao.class);
    private ConnectionPool connectionPool = ConnectionPool.getInstance();
    private Connection connection;
    private JdbcMapper mapper;

    public JdbcVacRespondedDao() {
        mapper = new JdbcMapper();
        try {
            connection = connectionPool.takeConnection();
        } catch (ConnectionPoolException e) {
            log.error(e);
        }
    }

    @Override
    public List<VacResponded> getAllVacRespondedBySeeker(User user) throws JdbcDaoException {
        List<VacResponded> vacRespondedList = null;
        if (connection != null) {
            vacRespondedList = new LinkedList<>();
            String query = "select * from vac_responded where user_id = ?";
            PreparedStatement statement = null;
            try {
                statement = connection.prepareStatement(query);
                statement.setInt(1, user.getId());

                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    vacRespondedList.add(mapper.vacRespondedMap(resultSet));
                }
            } catch (SQLException e) {
                log.error(e);
                throw new JdbcDaoException("Prepared statement error", e);
            } finally {
                connectionPool.closeConnection(connection, statement);
            }
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
        if (connection != null) {
            String query = "select * from vac_responded where user_id = ? and vacancy_id = ?";
            PreparedStatement statement = null;

            try {
                statement = connection.prepareStatement(query);
                statement.setInt(1, vacResponded.getUserId());
                statement.setInt(2, vacResponded.getVacancyId());

                ResultSet resultSet = statement.executeQuery();
                resultSet.last();
                return resultSet.getRow() != 0;
            } catch (SQLException e) {
                log.error(e);
                throw new JdbcDaoException("Prepared statement error", e);
            } finally {
                connectionPool.closeConnection(connection, statement);
            }
        }
        return false;
    }

    @Override
    public void create(VacResponded vacResponded) throws JdbcDaoException {

        if (!isExist(vacResponded) && connection != null) {
            String query = "insert into vac_responded (user_id,vacancy_id) values (? , ?)";
            PreparedStatement statement = null;
            try {
                statement = connection.prepareStatement(query);
                statement.setInt(1, vacResponded.getUserId());
                statement.setInt(2, vacResponded.getVacancyId());
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
    public VacResponded readById(int id) throws JdbcDaoException {
        VacResponded newVacResponded = null;
        if (connection != null) {
            String query = "select * from vac_responded where vacancy_id = ? and user_id = ? ";
            PreparedStatement statement = null;
            try {
                statement = connection.prepareStatement(query);
                statement.setInt(1, id);
                statement.setInt(2, id);

                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    newVacResponded = mapper.vacRespondedMap(resultSet);
                }
            } catch (SQLException e) {
                log.error(e);
                throw new JdbcDaoException("Prepared statement error", e);
            } finally {
                connectionPool.closeConnection(connection, statement);
            }
        }
        return newVacResponded;
    }

    @Override
    public VacResponded update(VacResponded vacResponded) throws JdbcDaoException {
        return null;
    }

    @Override
    public void deleteById(int id) throws JdbcDaoException {
        if (connection != null) {
            String query = "DELETE  from vac_responded where id = ?";
            PreparedStatement statement = null;
            try {
                statement = connection.prepareStatement(query);
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
