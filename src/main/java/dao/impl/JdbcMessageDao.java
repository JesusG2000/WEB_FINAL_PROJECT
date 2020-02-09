package dao.impl;

import bean.Message;
import bean.User;
import bean.dto.Dialog;
import dao.Dao;
import dao.MessageDao;
import dao.impl.util.JdbcMapper;
import dao.pool.ConnectionPool;
import exception.ConnectionPoolException;
import exception.JdbcDaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;

public class JdbcMessageDao implements MessageDao {
    private ConnectionPool connectionPool = ConnectionPool.getInstance();
    private Connection connection;
    private JdbcMapper mapper;

    public JdbcMessageDao() {
        mapper= new JdbcMapper();
        try {
            connection = connectionPool.takeConnection();
        } catch (ConnectionPoolException e) {

        }
    }

    @Override
    public List<Message> getAllMessageByUser(User user) throws JdbcDaoException {
        List<Message> list = null;
        if (connection != null) {
            list = new LinkedList<>();
            String query = "select * from message where getter_id = ? or sender_id = ?";
            PreparedStatement statement = null;
            try {
                statement = connection.prepareStatement(query);
                statement.setInt(1, user.getId());
                statement.setInt(2, user.getId());

                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    list.add(mapper.messageMap(resultSet));
                }
            } catch (SQLException e) {
                throw new JdbcDaoException("Prepared statement error", e);
            } finally {
                connectionPool.closeConnection(connection, statement);
            }
        }
        return list;
    }

    @Override
    public void deleteAllMessageByUsers(User ownUser, User otherUser) throws JdbcDaoException {
        if (connection != null) {
            String query = "delete from message where (getter_id = ? and sender_id = ?) or(getter_id = ? and sender_id = ?)";
            PreparedStatement statement = null;
            try {
                statement = connection.prepareStatement(query);
                statement.setInt(1, ownUser.getId());
                statement.setInt(2, otherUser.getId());
                statement.setInt(3, otherUser.getId());
                statement.setInt(4, ownUser.getId());
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new JdbcDaoException("Prepared statement error", e);
            } finally {
                connectionPool.closeConnection(connection, statement);
            }
        }
    }

    @Override
    public void deleteAllMessageByUser(User user) throws JdbcDaoException {
        List<Message> messageList = getAllMessageByUser(user);

        for (Message message : messageList) {
            deleteById(message.getId());
        }
    }

    @Override
    public List<Dialog> createDialogs(List<Message> messages, int ownerId) throws JdbcDaoException {
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
            User onwUser = userDao.readById(ownerId);
            User otherUser;
            if (getId != ownerId) {
                otherUser = userDao.readById(getId);
            } else {
                otherUser = userDao.readById(sendId);
            }


            dialog.setOwnUser(onwUser);
            dialog.setOtherUser(otherUser);
            dialogs.add(dialog);

        }
        dialogs = new LinkedList<>(new LinkedHashSet<>(dialogs));
        return dialogs;
    }

    @Override
    public void create(Message message) throws JdbcDaoException {
        if (connection != null) {
            String query = "Insert  into message (sender_id, getter_id, content) values (?,?,?)";
            PreparedStatement statement = null;
            try {
                statement = connection.prepareStatement(query);

                statement.setInt(1, message.getSenderId());
                statement.setInt(2, message.getGetterId());
                statement.setString(3, message.getContent());
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new JdbcDaoException("Prepared statement error", e);
            } finally {
                connectionPool.closeConnection(connection, statement);
            }
        }
    }

    @Override
    public Message readById(int id) throws JdbcDaoException {
        return null;
    }

    @Override
    public Message update(Message message) throws JdbcDaoException {
        return null;
    }

    @Override
    public void deleteById(int id) throws JdbcDaoException {
        if (connection != null) {
            String query = "delete from message where id = ?";
            PreparedStatement statement = null;
            try {
                statement = connection.prepareStatement(query);

                statement.setInt(1, id);
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new JdbcDaoException("Prepared statement error", e);
            } finally {
                connectionPool.closeConnection(connection, statement);
            }
        }
    }
}
