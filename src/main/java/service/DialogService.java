package service;

import bean.User;
import bean.dto.Dialog;

import java.util.List;

public interface DialogService {
    Dialog getDialogByUsers(List<Dialog> list , User ownUser , User otherUser);
}
