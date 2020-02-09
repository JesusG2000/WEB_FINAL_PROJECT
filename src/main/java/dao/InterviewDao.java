package dao;

import bean.Interview;
import bean.User;
import exception.JdbcDaoException;

import java.util.List;

public interface InterviewDao extends Dao<Interview> {
    List<Interview> getAllInterviewBySeeker(User user) throws JdbcDaoException;

    void deleteAllInterviewBySeeker(User user) throws JdbcDaoException;
}
