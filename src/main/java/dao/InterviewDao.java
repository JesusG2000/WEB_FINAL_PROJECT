package dao;

import bean.Interview;
import bean.User;

import java.util.List;

public interface InterviewDao {
    List<Interview> getAllInterviewBySeekerId(User user);
}
