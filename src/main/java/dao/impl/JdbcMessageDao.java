package dao.impl;

import bean.Message;
import bean.User;
import dao.Dao;
import dao.MessageDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class JdbcMessageDao extends Dao<Message> implements MessageDao {
    @Override
    public boolean create(Message message) {
        String query = "Insert  into message (sender_id, getter_id, content) values (?,?,?)";
        try {
            PreparedStatement statement = getConnection().prepareStatement(query);
            statement.setInt(1, message.getSenderId());
            statement.setInt(2, message.getGetterId());
            statement.setString(3, message.getContent());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Message read(Message message) {
        return null;
    }

    private Message messageMap(ResultSet resultSet) throws SQLException {
        Message message = new Message();
        message.setId(resultSet.getInt("id"));
        message.setSenderId(resultSet.getInt("sender_id"));
        message.setGetterId(resultSet.getInt("getter_id"));
        message.setContent(resultSet.getString("content"));
        return message;
    }

    @Override
    public Message update(Message message) {
        return null;
    }

    @Override
    public Message delete(Message message) {
        return null;
    }

    @Override
    public List<Message> getAllMessageByUser(User user) {
        List<Message> list = new LinkedList<>();
        String query = "select * from message where getter_id = ? or sender_id = ?";
        try {
            PreparedStatement statement = getConnection().prepareStatement(query);
            statement.setInt(1, user.getId());
            statement.setInt(2, user.getId());

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(messageMap(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
