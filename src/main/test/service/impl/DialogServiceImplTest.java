package service.impl;

import bean.Message;
import bean.User;
import bean.dto.Dialog;
import dao.MessageDao;
import dao.impl.JdbcMessageDao;
import org.junit.Test;
import service.DialogService;
import service.MessageService;

import java.util.List;

import static org.junit.Assert.*;

public class DialogServiceImplTest {

    @Test
    public void getDialogsByUsers() {
        User user1 = new User(1);
        User user2 = new User(2);

        MessageDao messageDao = new JdbcMessageDao();
        MessageService service = new MessageServiceImpl();
        DialogService dialogService = new DialogServiceImpl();

        List<Message> messages = messageDao.getAllMessageByUser(user1);

        List<Dialog> dialogs = service.createDialogs(messages,user1.getId());

        Dialog finalDialog = dialogService.getDialogByUsers(dialogs,user1,user2);

        System.out.println(finalDialog);
    }
}