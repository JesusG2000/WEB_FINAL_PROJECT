package by.epam.service;

import by.epam.bean.User;
import by.epam.bean.Vacancy;
import by.epam.exception.ServiceException;

import java.util.List;

public interface UserService extends Service<User> {
    User getUserByName(String name) throws ServiceException;

    List<User> getAllSeekers() throws ServiceException;

    List<User> getAllHR() throws ServiceException;

    List<User> getAllByVacancy(Vacancy vacancy) throws ServiceException;

    boolean isRegistered(User user) throws ServiceException;

    boolean isExist(User user) throws ServiceException;
}
