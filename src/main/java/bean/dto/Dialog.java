package bean.dto;

import bean.Message;
import bean.User;

import java.util.List;
import java.util.Objects;

public class Dialog {
    private List<Message> messages;
    private User otherUser;
    private User ownUser;

    public Dialog(List<Message> messages, User otherUser, User ownUser) {
        this.messages = messages;
        this.otherUser = otherUser;
        this.ownUser = ownUser;
    }

    public Dialog(List<Message> messages) {
        this.messages = messages;
    }

    public Dialog() {
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public User getOtherUser() {
        return otherUser;
    }

    public void setOtherUser(User otherUser) {
        this.otherUser = otherUser;
    }

    public User getOwnUser() {
        return ownUser;
    }

    public void setOwnUser(User ownUser) {
        this.ownUser = ownUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dialog dialog = (Dialog) o;
        return Objects.equals(messages, dialog.messages) &&
                Objects.equals(otherUser, dialog.otherUser) &&
                Objects.equals(ownUser, dialog.ownUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(messages, otherUser, ownUser);
    }

    @Override
    public String toString() {
        return "Dialog{" +
                "messages=" + messages +
                ", otherUser=" + otherUser +
                ", ownUser=" + ownUser +
                '}';
    }
}
