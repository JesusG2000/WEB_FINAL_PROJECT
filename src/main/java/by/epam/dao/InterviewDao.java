package by.epam.dao;

import by.epam.bean.Interview;
import by.epam.bean.User;
import by.epam.exception.JdbcDaoException;

import java.util.List;

public interface InterviewDao extends Dao<Interview> {
    List<Interview> getAllInterviewBySeeker(User user) throws JdbcDaoException;

    void deleteAllInterviewBySeeker(User user) throws JdbcDaoException;
}
