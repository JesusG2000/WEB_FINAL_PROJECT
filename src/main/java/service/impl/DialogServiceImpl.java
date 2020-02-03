package service.impl;

import bean.User;
import bean.dto.Dialog;
import service.DialogService;

import java.util.LinkedList;
import java.util.List;

public class DialogServiceImpl implements DialogService {
    @Override
    public Dialog getDialogByUsers(List<Dialog> list, User ownUser, User otherUser) {
        Dialog dialogs= new Dialog();
        for (Dialog d : list) {
            if ((d.getOwnUser().getId()==ownUser.getId() && d.getOtherUser().getId()==otherUser.getId())
                    || (d.getOtherUser().getId()==ownUser.getId() && d.getOwnUser().getId()==otherUser.getId())) {
                dialogs=d;
            }
        }
        return dialogs;
    }
}
