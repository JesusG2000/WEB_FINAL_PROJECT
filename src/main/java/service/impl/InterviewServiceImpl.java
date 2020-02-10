package service.impl;

import bean.Interview;
import bean.User;
import dao.DaoFactory;
import dao.InterviewDao;
import exception.JdbcDaoException;
import exception.ServiceException;
import org.apache.log4j.Logger;
import service.InterviewService;

import java.util.List;

public class InterviewServiceImpl implements InterviewService {
    private static Logger log = Logger.getLogger(InterviewServiceImpl.class);
    private InterviewDao interviewDao = DaoFactory.getInstance().getInterviewDao();
    @Override
    public List<Interview> getAllInterviewBySeeker(User user) throws ServiceException {
        try {
            return interviewDao.getAllInterviewBySeeker(user);
        } catch (JdbcDaoException e) {
            log.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteAllInterviewBySeeker(User user) throws ServiceException {
        try {
            interviewDao.deleteAllInterviewBySeeker(user);
        } catch (JdbcDaoException e) {
            log.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void create(Interview interview)throws ServiceException {
        try {
            interviewDao.create(interview);
        } catch (JdbcDaoException e) {
            log.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public Interview readById(int id)  throws ServiceException  {
        try {
            return interviewDao.readById(id);
        } catch (JdbcDaoException e) {
            log.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public Interview update(Interview interview)throws ServiceException {
        try {
            return interviewDao.update(interview);
        } catch (JdbcDaoException e) {
            log.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteById(int id)throws ServiceException {
        try {
            interviewDao.deleteById(id);
        } catch (JdbcDaoException e) {
            log.error(e);
            throw new ServiceException(e);
        }
    }
}
