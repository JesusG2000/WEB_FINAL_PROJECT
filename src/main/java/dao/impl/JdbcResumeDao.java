package dao.impl;

import bean.Resume;
import dao.ResumeDao;
import dao.impl.util.JdbcMapper;
import dao.pool.ConnectionPool;
import exception.ConnectionPoolException;
import exception.JdbcDaoException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcResumeDao implements ResumeDao {
    private static Logger log = Logger.getLogger(JdbcResumeDao.class);
    private JdbcMapper mapper = new JdbcMapper();
    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public void create(Resume resume) throws JdbcDaoException {
        String query = "INSERT into resume" +
                "(user_id,vacancy_id,user_name,user_surname,user_age,user_language,user_work_experience,user_info)" +
                "values (?,?,?,?,?,?,?,?)";
        PreparedStatement statement = null;
        Connection connection = null;
        try {
            connection = connectionPool.takeConnection();
            statement = connection.prepareStatement(query);

            statement.setInt(1, resume.getUserId());
            statement.setInt(2, resume.getVacancyId());
            statement.setString(3, resume.getUserName());
            statement.setString(4, resume.getUserSurname());
            statement.setInt(5, resume.getUserAge());
            statement.setString(6, resume.getUserLanguage());
            statement.setString(7, resume.getUserWorkExperience());
            statement.setString(8, resume.getUserInfo());
            statement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            log.error(e);
            throw new JdbcDaoException("Prepared statement error", e);
        } finally {
            connectionPool.closeConnection(connection, statement);
        }
    }

    @Override
    public Resume readById(int id) throws JdbcDaoException {
        Resume resume = null;

        String query = "select * from resume where id = ?";
        PreparedStatement statement = null;
        Connection connection = null;
        ResultSet resultSet = null;

        try {
            connection = connectionPool.takeConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                resume = mapper.resumeMap(resultSet);
            }
        } catch (SQLException | ConnectionPoolException e) {
            log.error(e);
            throw new JdbcDaoException("Prepared statement error", e);
        } finally {
            connectionPool.closeConnection(connection, statement, resultSet);
        }
        return resume;

    }

    @Override
    public Resume update(Resume resume) throws JdbcDaoException {
        String sqlInsertUser = "UPDATE resume set user_name = ? , user_surname = ? ,user_age = ?  , user_language = ?" +
                ", user_work_experience = ? , user_info = ? where id = ? ";

        PreparedStatement statement = null;
        Connection connection = null;
        try {
            connection = connectionPool.takeConnection();
            statement = connection.prepareStatement(sqlInsertUser);

            statement.setString(1, resume.getUserName());
            statement.setString(2, resume.getUserSurname());
            statement.setInt(3, resume.getUserAge());
            statement.setString(4, resume.getUserLanguage());
            statement.setString(5, resume.getUserWorkExperience());
            statement.setString(6, resume.getUserInfo());
            statement.setInt(7, resume.getId());

            statement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            log.error(e);
            throw new JdbcDaoException("Prepared statement error", e);
        } finally {
            connectionPool.closeConnection(connection, statement);
        }

        return resume;
    }

    @Override
    public void deleteById(int id) throws JdbcDaoException {

    }

    @Override
    public Resume readByUserIdVacancyId(int userId, int vacancyId) throws JdbcDaoException {
        Resume resume = null;

        String query = "select * from resume where user_id = ? and vacancy_id=?";
        PreparedStatement statement = null;
        Connection connection = null;
        ResultSet resultSet = null;

        try {
            connection = connectionPool.takeConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, userId);
            statement.setInt(2, vacancyId);

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                resume = mapper.resumeMap(resultSet);
            }
        } catch (SQLException | ConnectionPoolException e) {
            log.error(e);
            throw new JdbcDaoException("Prepared statement error", e);
        } finally {
            connectionPool.closeConnection(connection, statement, resultSet);
        }
        return resume;
    }
}
