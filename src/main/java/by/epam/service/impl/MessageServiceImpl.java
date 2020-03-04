package by.epam.service.impl;

import by.epam.bean.Message;
import by.epam.bean.User;
import by.epam.bean.dto.Dialog;
import by.epam.dao.DaoFactory;
import by.epam.dao.MessageDao;
import by.epam.exception.JdbcDaoException;
import by.epam.exception.ServiceException;
import org.apache.log4j.Logger;
import by.epam.service.MessageService;

import java.util.List;

public class MessageServiceImpl implements MessageService {
    private static Logger log = Logger.getLogger(MessageServiceImpl.class);
    private MessageDao messageDao = DaoFactory.getInstance().getMessageDao();

    @Override
    public List<Message> getAllMessageByUser(User user) throws ServiceException {
        try {
            return messageDao.getAllMessageByUser(user);
        } catch (JdbcDaoException e) {
            log.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteAllMessageByUsers(User ownUser, User otherUser) throws ServiceException {
        try {
            messageDao.deleteAllMessageByUsers(ownUser, otherUser);
        } catch (JdbcDaoException e) {
            log.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteAllMessageByUser(User user) throws ServiceException {
        try {
            messageDao.deleteAllMessageByUser(user);
        } catch (JdbcDaoException e) {
            log.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Dialog> createDialogs(List<Message> messages, int ownerId) throws ServiceException {
        try {
            return messageDao.createDialogs(messages, ownerId);
        } catch (JdbcDaoException e) {
            log.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void create(Message message) throws ServiceException {
        try {
            messageDao.create(message);
        } catch (JdbcDaoException e) {
            log.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public Message readById(int id) throws ServiceException {
        try {
            return messageDao.readById(id);
        } catch (JdbcDaoException e) {
            log.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public Message update(Message message) throws ServiceException {
        try {
            return messageDao.update(message);
        } catch (JdbcDaoException e) {
            log.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteById(int id) throws ServiceException {
        try {
            messageDao.deleteById(id);
        } catch (JdbcDaoException e) {
            log.error(e);
            throw new ServiceException(e);
        }
    }
}
