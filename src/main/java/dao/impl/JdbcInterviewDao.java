package dao.impl;

import bean.Interview;
import bean.User;
import dao.InterviewDao;
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

public class JdbcInterviewDao implements InterviewDao {
    private ConnectionPool connectionPool = ConnectionPool.getInstance();
    private Connection connection;
    private JdbcMapper mapper;

    public JdbcInterviewDao() {
        mapper = new JdbcMapper();
        try {
            connection = connectionPool.takeConnection();
        } catch (ConnectionPoolException e) {

        }
    }

    @Override
    public List<Interview> getAllInterviewBySeeker(User user) throws JdbcDaoException {
        List<Interview> list = null;
        if (connection != null) {
            list = new LinkedList<>();
            String query = "select * from interview  where seeker_id = ? ";
            PreparedStatement statement = null;
            try {
                statement = connection.prepareStatement(query);

                statement.setInt(1, user.getId());

                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    list.add(mapper.interviewMap(resultSet));
                }
            } catch (SQLException e) {
                throw new JdbcDaoException("Prepared statement error", e);
            } finally {
                connectionPool.closeConnection(connection, statement);
            }
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
        if (connection != null) {
            String query = "INSERT into interview (hr_id, seeker_id, date, comment ,vacancy_id) values (?,?,?,?,?)";
            PreparedStatement statement = null;
            try {
                statement = connection.prepareStatement(query);

                statement.setInt(1, interview.getHrId());
                statement.setInt(2, interview.getSeekerId());
                statement.setDate(3, interview.getDate());
                statement.setString(4, interview.getComment());
                statement.setInt(5, interview.getVacancyId());
                statement.executeUpdate();

            } catch (SQLException e) {
                throw new JdbcDaoException("Prepared statement error", e);
            } finally {
                connectionPool.closeConnection(connection, statement);
            }
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
        if (connection != null) {
            String query = "delete from interview where id = ?";
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
