package dao;

import bean.Vacancy;
import exception.JdbcDaoException;

import java.util.List;

public interface VacancyDao extends Dao<Vacancy> {
    List<Vacancy> getAllVacancy() throws JdbcDaoException;

    boolean isExist(Vacancy vacancy) throws JdbcDaoException;
}
