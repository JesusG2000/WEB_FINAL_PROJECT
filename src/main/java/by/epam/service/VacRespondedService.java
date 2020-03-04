package by.epam.service;

import by.epam.bean.User;
import by.epam.bean.VacResponded;
import by.epam.exception.ServiceException;

import java.util.List;

public interface VacRespondedService extends Service<VacResponded> {
    List<VacResponded> getAllVacRespondedBySeeker(User user) throws ServiceException;

    void deleteAllVacRespondedBySeeker(User user) throws ServiceException;

    boolean isExist(VacResponded vacResponded) throws ServiceException;

}
