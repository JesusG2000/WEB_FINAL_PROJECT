package by.epam.service.impl;

import by.epam.bean.Resume;
import by.epam.dao.DaoFactory;
import by.epam.dao.ResumeDao;
import by.epam.exception.JdbcDaoException;
import by.epam.exception.ServiceException;
import org.apache.log4j.Logger;
import by.epam.service.ResumeService;

public class ResumeServiceImpl implements ResumeService {
    private static Logger log = Logger.getLogger(ResumeServiceImpl.class);
    private ResumeDao resumeDao = DaoFactory.getInstance().getResumeDao();

    @Override
    public void create(Resume resume) throws ServiceException {
        try {
            resumeDao.create(resume);
        } catch (JdbcDaoException e) {
            log.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public Resume readById(int id) throws ServiceException {
        try {
            return resumeDao.readById(id);
        } catch (JdbcDaoException e) {
            log.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public Resume update(Resume resume) throws ServiceException {
        try {
            return resumeDao.update(resume);
        } catch (JdbcDaoException e) {
            log.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteById(int id) throws ServiceException {
        try {
            resumeDao.deleteById(id);
        } catch (JdbcDaoException e) {
            log.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public Resume readByUserIdVacancyId(int userId, int vacancyId) throws ServiceException {
        try {
            return resumeDao.readByUserIdVacancyId(userId, vacancyId);
        } catch (JdbcDaoException e) {
            log.error(e);
            throw new ServiceException(e);
        }
    }
}
