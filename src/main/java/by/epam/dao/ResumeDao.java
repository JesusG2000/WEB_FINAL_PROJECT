package by.epam.dao;

import by.epam.bean.Resume;
import by.epam.exception.JdbcDaoException;

public interface ResumeDao extends Dao<Resume> {
    Resume readByUserIdVacancyId(int userId, int vacancyId) throws JdbcDaoException;
}
