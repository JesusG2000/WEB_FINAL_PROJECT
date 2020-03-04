package by.epam.service;

import by.epam.bean.Message;
import by.epam.bean.User;
import by.epam.bean.dto.Dialog;
import by.epam.exception.ServiceException;

import java.util.List;

public interface MessageService extends Service<Message> {
    List<Message> getAllMessageByUser(User user) throws ServiceException;

    void deleteAllMessageByUsers(User ownUser, User otherUser) throws ServiceException;

    void deleteAllMessageByUser(User user) throws ServiceException;

    List<Dialog> createDialogs(List<Message> messages, int ownerId) throws ServiceException;
}
