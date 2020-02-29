package dao;

import bean.Resume;
import exception.JdbcDaoException;

public interface ResumeDao extends Dao<Resume> {
    Resume readByUserIdVacancyId(int userId , int vacancyId) throws JdbcDaoException;
}
