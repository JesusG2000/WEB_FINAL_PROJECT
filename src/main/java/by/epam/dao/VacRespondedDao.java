package by.epam.dao;

import by.epam.bean.User;
import by.epam.bean.VacResponded;
import by.epam.exception.JdbcDaoException;

import java.util.List;

public interface VacRespondedDao extends Dao<VacResponded> {
    List<VacResponded> getAllVacRespondedBySeeker(User user) throws JdbcDaoException;

    void deleteAllVacRespondedBySeeker(User user) throws JdbcDaoException;

    boolean isExist(VacResponded vacResponded) throws JdbcDaoException;
}
