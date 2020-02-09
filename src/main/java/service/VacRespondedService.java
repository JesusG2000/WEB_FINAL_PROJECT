package service;

import bean.User;
import bean.VacResponded;
import exception.ServiceException;

import java.util.List;

public interface VacRespondedService extends Service<VacResponded> {
    List<VacResponded> getAllVacRespondedBySeeker(User user) throws ServiceException;

    void deleteAllVacRespondedBySeeker(User user) throws ServiceException;

    boolean isExist(VacResponded vacResponded) throws ServiceException;

}
