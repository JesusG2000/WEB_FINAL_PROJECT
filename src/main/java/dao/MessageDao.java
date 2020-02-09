package dao;

import bean.Message;
import bean.User;
import bean.dto.Dialog;
import exception.JdbcDaoException;

import java.util.List;

public interface MessageDao extends Dao<Message> {
    List<Message> getAllMessageByUser(User user) throws JdbcDaoException;

    void deleteAllMessageByUsers(User ownUser, User otherUser) throws JdbcDaoException;

    void deleteAllMessageByUser(User user) throws JdbcDaoException;

    List<Dialog> createDialogs(List<Message> messages, int ownerId) throws JdbcDaoException;
}
