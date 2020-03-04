package by.epam.dao;

import by.epam.bean.User;
import by.epam.bean.Vacancy;
import by.epam.exception.JdbcDaoException;

import java.util.List;

public interface UserDao extends Dao<User> {
    User getUserByName(String name) throws JdbcDaoException;

    List<User> getAllSeekers() throws JdbcDaoException;

    List<User> getAllHR() throws JdbcDaoException;

    List<User> getAllByVacancy(Vacancy vacancy) throws JdbcDaoException;

    boolean isRegistered(User user) throws JdbcDaoException;

    boolean isExist(User user) throws JdbcDaoException;
}
