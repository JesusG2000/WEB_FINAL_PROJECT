package service.impl;

import bean.Vacancy;
import dao.DaoFactory;
import dao.VacancyDao;
import exception.JdbcDaoException;
import exception.ServiceException;
import service.VacancyService;

import java.util.List;

public class VacancyServiceImpl implements VacancyService {
    private VacancyDao vacancyDao = DaoFactory.getInstance().getVacancyDao();

    @Override
    public List<Vacancy> getAllVacancy()throws ServiceException {
        try {
            return vacancyDao.getAllVacancy();
        } catch (JdbcDaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void create(Vacancy vacancy)throws ServiceException {
        try {
            vacancyDao.create(vacancy);
        } catch (JdbcDaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Vacancy readById(int id)throws ServiceException {
        try {
            return vacancyDao.readById(id);
        } catch (JdbcDaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Vacancy update(Vacancy vacancy)throws ServiceException {
        try {
            return vacancyDao.update(vacancy);
        } catch (JdbcDaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteById(int id)throws ServiceException {
        try {
            vacancyDao.deleteById(id);
        } catch (JdbcDaoException e) {
            throw new ServiceException(e);
        }
    }
}
