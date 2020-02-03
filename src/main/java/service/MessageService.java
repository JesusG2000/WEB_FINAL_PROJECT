package service;

import bean.Message;
import bean.dto.Dialog;

import java.util.List;

public interface MessageService {
    List<Dialog> createDialogs(List<Message> messages ,int ownerId);
}
