package by.epam.service;

import by.epam.bean.Interview;
import by.epam.bean.User;
import by.epam.exception.ServiceException;

import java.util.List;

public interface InterviewService extends Service<Interview> {
    List<Interview> getAllInterviewBySeeker(User user) throws ServiceException;

    void deleteAllInterviewBySeeker(User user) throws ServiceException;
}
