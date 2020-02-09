package dao;

import dao.impl.*;

public final class DaoFactory {
    private static final DaoFactory instance = new DaoFactory();
    private static final InterviewDao interviewDao = new JdbcInterviewDao();
    private static final MessageDao messageDao = new JdbcMessageDao();
    private static final UserDao userDao = new JdbcUserDao();
    private static final VacancyDao vacancyDao = new JdbcVacancyDao();
    private static final VacRespondedDao vacRespondedDao = new JdbcVacRespondedDao();

    private DaoFactory() {
    }

    public static DaoFactory getInstance() {
        return instance;
    }

    public InterviewDao getInterviewDao() {
        return interviewDao;
    }

    public MessageDao getMessageDao() {
        return messageDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public VacancyDao getVacancyDao() {
        return vacancyDao;
    }

    public VacRespondedDao getVacRespondedDao() {
        return vacRespondedDao;
    }
}
