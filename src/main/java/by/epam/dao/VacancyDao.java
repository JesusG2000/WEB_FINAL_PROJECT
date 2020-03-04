package by.epam.dao;

import by.epam.bean.Vacancy;
import by.epam.exception.JdbcDaoException;

import java.util.List;

public interface VacancyDao extends Dao<Vacancy> {
    List<Vacancy> getAllVacancy() throws JdbcDaoException;

    boolean isExist(Vacancy vacancy) throws JdbcDaoException;
}
