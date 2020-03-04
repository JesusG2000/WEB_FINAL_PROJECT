package by.epam.dao.impl;

import by.epam.bean.Interview;
import by.epam.bean.User;
import by.epam.dao.InterviewDao;
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

public class JdbcInterviewDao implements InterviewDao {
    private static Logger log = Logger.getLogger(JdbcInterviewDao.class);
    private ConnectionPool connectionPool = ConnectionPool.getInstance();
    private JdbcMapper mapper = new JdbcMapper();


    @Override
    public List<Interview> getAllInterviewBySeeker(User user) throws JdbcDaoException {
        List<Interview> list = null;


        String query = "select * from interview  where seeker_id = ? ";
        PreparedStatement statement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        try {
            list = new LinkedList<>();
            connection = connectionPool.takeConnection();
            statement = connection.prepareStatement(query);

            statement.setInt(1, user.getId());

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                list.add(mapper.interviewMap(resultSet));
            }
        } catch (SQLException | ConnectionPoolException e) {
            log.error(e);
            throw new JdbcDaoException("Prepared statement error", e);
        } finally {
            connectionPool.closeConnection(connection, statement, resultSet);
        }

        return list;
    }

    @Override
    public void deleteAllInterviewBySeeker(User user) throws JdbcDaoException {
        List<Interview> interviews = getAllInterviewBySeeker(user);
        for (Interview interview : interviews) {
            deleteById(interview.getId());
        }
    }

    @Override
    public void create(Interview interview) throws JdbcDaoException {

        String query = "INSERT into interview (hr_id, seeker_id, date, comment ,vacancy_id) values (?,?,?,?,?)";
        PreparedStatement statement = null;
        Connection connection = null;
        try {
            connection = connectionPool.takeConnection();
            statement = connection.prepareStatement(query);

            statement.setInt(1, interview.getHrId());
            statement.setInt(2, interview.getSeekerId());
            statement.setDate(3, interview.getDate());
            statement.setString(4, interview.getComment());
            statement.setInt(5, interview.getVacancyId());
            statement.executeUpdate();

        } catch (SQLException | ConnectionPoolException e) {
            log.error(e);
            throw new JdbcDaoException("Prepared statement error", e);
        } finally {
            connectionPool.closeConnection(connection, statement);
        }
    }

    @Override
    public Interview readById(int id) throws JdbcDaoException {
        return null;
    }

    @Override
    public Interview update(Interview interview) throws JdbcDaoException {
        return null;
    }

    @Override
    public void deleteById(int id) throws JdbcDaoException {

        String query = "delete from interview where id = ?";
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
