package by.epam.dao;

import by.epam.bean.Message;
import by.epam.bean.User;
import by.epam.bean.dto.Dialog;
import by.epam.exception.JdbcDaoException;

import java.util.List;

public interface MessageDao extends Dao<Message> {
    List<Message> getAllMessageByUser(User user) throws JdbcDaoException;

    void deleteAllMessageByUsers(User ownUser, User otherUser) throws JdbcDaoException;

    void deleteAllMessageByUser(User user) throws JdbcDaoException;

    List<Dialog> createDialogs(List<Message> messages, int ownerId) throws JdbcDaoException;
}
