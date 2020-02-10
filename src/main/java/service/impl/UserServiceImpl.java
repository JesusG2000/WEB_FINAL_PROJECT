package service.impl;

import bean.User;
import bean.Vacancy;
import dao.DaoFactory;
import dao.UserDao;
import exception.JdbcDaoException;
import exception.ServiceException;
import org.apache.log4j.Logger;
import service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private static Logger log = Logger.getLogger(UserServiceImpl.class);
    private UserDao userDao = DaoFactory.getInstance().getUserDao();

    @Override
    public User getUserByName(String name) throws ServiceException {
        try {
            return userDao.getUserByName(name);
        } catch (JdbcDaoException e) {
            log.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> getAllSeekers() throws ServiceException{
        try {
            return userDao.getAllSeekers();
        } catch (JdbcDaoException e) {
            log.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> getAllHR()throws ServiceException {
        try {
            return userDao.getAllHR();
        } catch (JdbcDaoException e) {
            log.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> getAllByVacancy(Vacancy vacancy)throws ServiceException {
        try {
            return userDao.getAllByVacancy(vacancy);
        } catch (JdbcDaoException e) {
            log.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean isRegistered(User user)throws ServiceException {
        try {
            return userDao.isRegistered(user);
        } catch (JdbcDaoException e) {
            log.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean isExist(User user)throws ServiceException {
        try {
            return userDao.isExist(user);
        } catch (JdbcDaoException e) {
            log.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void create(User user)throws ServiceException {
        try {
            userDao.create(user);
        } catch (JdbcDaoException e) {
            log.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public User readById(int id)throws ServiceException {
        try {
            return userDao.readById(id);
        } catch (JdbcDaoException e) {
            log.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public User update(User user)throws ServiceException {
        try {
            return userDao.update(user);
        } catch (JdbcDaoException e) {
            log.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteById(int id) throws ServiceException{
        try {
            userDao.deleteById(id);
        } catch (JdbcDaoException e) {
            log.error(e);
            throw new ServiceException(e);
        }
    }
}
