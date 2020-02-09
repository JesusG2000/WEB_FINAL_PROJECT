package dao;

import bean.User;
import bean.VacResponded;
import exception.JdbcDaoException;

import java.util.List;

public interface VacRespondedDao extends Dao<VacResponded> {
    List<VacResponded> getAllVacRespondedBySeeker(User user) throws JdbcDaoException;

    void deleteAllVacRespondedBySeeker(User user) throws JdbcDaoException;

    boolean isExist(VacResponded vacResponded) throws JdbcDaoException;
}
