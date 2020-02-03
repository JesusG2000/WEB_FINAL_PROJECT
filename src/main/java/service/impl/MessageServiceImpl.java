package service.impl;

import bean.Message;
import bean.User;
import bean.dto.Dialog;
import dao.Dao;
import dao.UserDao;
import dao.impl.JdbcUserDao;
import service.MessageService;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;

public class MessageServiceImpl implements MessageService {
    @Override
    public List<Dialog> createDialogs(List<Message> messages , int ownerId) {
        List<Dialog> dialogs = new LinkedList<>();
        Dao<User> userDao = new JdbcUserDao();

        for (int i = 0; i < messages.size(); i++) {
            int getId = messages.get(i).getGetterId();
            int sendId = messages.get(i).getSenderId();

            List<Message> messageList = new LinkedList<>();

            for (int y = 0; y < messages.size(); y++) {
                if ((getId == messages.get(y).getGetterId() && sendId == messages.get(y).getSenderId())
                        || (getId == messages.get(y).getSenderId() && sendId == messages.get(y).getGetterId())) {
                    messageList.add(messages.get(y));
                }
            }
            Dialog dialog = new Dialog(messageList);
            User onwUser = userDao.read(new User(ownerId));
            User otherUser;
            if(getId!=ownerId){
                otherUser = userDao.read(new User(getId));
            }else{
                otherUser = userDao.read(new User(sendId));
            }


           dialog.setOwnUser(onwUser);
           dialog.setOtherUser(otherUser);
           dialogs.add(dialog);

        }
        dialogs = new LinkedList<>(new LinkedHashSet<>(dialogs));
        return dialogs;
    }
}

