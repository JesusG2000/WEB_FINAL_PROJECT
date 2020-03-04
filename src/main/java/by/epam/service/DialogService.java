package by.epam.service;

import by.epam.bean.User;
import by.epam.bean.dto.Dialog;

import java.util.List;

public interface DialogService {
    Dialog getDialogByUsers(List<Dialog> list, User ownUser, User otherUser);
}
