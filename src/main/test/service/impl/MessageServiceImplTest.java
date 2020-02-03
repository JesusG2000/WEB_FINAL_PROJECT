package service.impl;

import bean.Message;
import bean.User;
import bean.dto.Dialog;
import dao.MessageDao;
import dao.impl.JdbcMessageDao;
import org.junit.Test;
import service.MessageService;

import java.util.List;

public class MessageServiceImplTest {

    @Test
    public void createDialogs() {
        User user = new User(1, "", "");
        MessageDao messageDao = new JdbcMessageDao();
        List<Message> messages = messageDao.getAllMessageByUser(user);
        MessageService service = new MessageServiceImpl();
        List<Dialog> dialogs = service.createDialogs(messages,user.getId());
        for (Dialog d : dialogs ) {
            System.out.println(d);
        }
    }
}