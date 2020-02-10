package service.impl;

import bean.User;
import bean.VacResponded;
import dao.DaoFactory;
import dao.VacRespondedDao;
import exception.JdbcDaoException;
import exception.ServiceException;
import org.apache.log4j.Logger;
import service.VacRespondedService;

import java.util.List;

public class VacRespondedServiceImpl implements VacRespondedService {
    private static Logger log = Logger.getLogger(VacRespondedServiceImpl.class);
    private VacRespondedDao vacRespondedDao = DaoFactory.getInstance().getVacRespondedDao();

    @Override
    public List<VacResponded> getAllVacRespondedBySeeker(User user)throws ServiceException {
        try {
            return vacRespondedDao.getAllVacRespondedBySeeker(user);
        } catch (JdbcDaoException e) {
            log.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteAllVacRespondedBySeeker(User user)throws ServiceException {
        try {
            vacRespondedDao.deleteAllVacRespondedBySeeker(user);
        } catch (JdbcDaoException e) {
            log.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean isExist(VacResponded vacResponded)throws ServiceException {
        try {
            return vacRespondedDao.isExist(vacResponded);
        } catch (JdbcDaoException e) {
            log.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void create(VacResponded vacResponded)throws ServiceException {
        try {
            vacRespondedDao.create(vacResponded);
        } catch (JdbcDaoException e) {
            log.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public VacResponded readById(int id)throws ServiceException {
        return null;
    }

    @Override
    public VacResponded update(VacResponded vacResponded)throws ServiceException {
        try {
            return vacRespondedDao.update(vacResponded);
        } catch (JdbcDaoException e) {
            log.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteById(int id)throws ServiceException {
        try {
            vacRespondedDao.deleteById(id);
        } catch (JdbcDaoException e) {
            log.error(e);
            throw new ServiceException(e);
        }
    }
}
