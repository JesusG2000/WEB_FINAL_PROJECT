package dao;

import bean.Message;
import bean.User;

import java.util.List;

public interface MessageDao {
    List<Message> getAllMessageByUser(User user);
}
