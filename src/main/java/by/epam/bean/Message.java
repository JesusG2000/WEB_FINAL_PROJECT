package by.epam.bean;

import java.util.Objects;

public class Message {
    private int id;
    private int senderId;
    private int getterId;
    private String content;

    public Message(int id, int senderId, int getterId, String content) {
        this.id = id;
        this.senderId = senderId;
        this.getterId = getterId;
        this.content = content;
    }

    public Message(int senderId, int getterId, String content) {
        this.senderId = senderId;
        this.getterId = getterId;
        this.content = content;
    }

    public Message() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getGetterId() {
        return getterId;
    }

    public void setGetterId(int getterId) {
        this.getterId = getterId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return id == message.id &&
                senderId == message.senderId &&
                getterId == message.getterId &&
                Objects.equals(content, message.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, senderId, getterId, content);
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", senderId=" + senderId +
                ", getterId=" + getterId +
                ", content='" + content + '\'' +
                '}';
    }
}
