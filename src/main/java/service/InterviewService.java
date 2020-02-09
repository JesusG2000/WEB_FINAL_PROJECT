package service;

import bean.Interview;
import bean.User;
import exception.ServiceException;

import java.util.List;

public interface InterviewService extends Service<Interview> {
    List<Interview> getAllInterviewBySeeker(User user) throws ServiceException;

    void deleteAllInterviewBySeeker(User user) throws ServiceException;
}
