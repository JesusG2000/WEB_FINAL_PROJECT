package by.epam.dao.pool;

import by.epam.exception.ConnectionPoolException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {

    private final static Logger logger = LogManager.getLogger(ConnectionPool.class);
    private static ConnectionPool instance;
    private static ReentrantLock lock = new ReentrantLock();

    private BlockingQueue<Connection> connectionQueue;
    private BlockingQueue<Connection> givenAwayConnectionQueue;

    private String driver;
    private String url;
    private String user;
    private String password;
    private int poolSize;


    private ConnectionPool() {
        DBResourceManager dbResourceManager = DBResourceManager.getInstance();
        driver = dbResourceManager.getValue(DBParameter.DB_DRIVER);
        url = dbResourceManager.getValue(DBParameter.DB_URL);
        user = dbResourceManager.getValue(DBParameter.DB_USER);
        password = dbResourceManager.getValue(DBParameter.DB_PASSWORD);
        poolSize = Integer.parseInt(dbResourceManager.getValue(DBParameter.DB_POOL_SIZE));
        try {
            initPoolData();
        } catch (ConnectionPoolException e) {
            logger.error("Can't init pool data");
        }
    }

    public static ConnectionPool getInstance() {
        if (instance == null) {
            lock.lock();
            if (instance == null) {
                instance = new ConnectionPool();
            }
            lock.unlock();
        }
        return instance;
    }

    public void initPoolData() throws ConnectionPoolException {
        try {
            Class.forName(driver);

            givenAwayConnectionQueue = new ArrayBlockingQueue<Connection>(poolSize);
            connectionQueue = new ArrayBlockingQueue<Connection>(poolSize);
            for (int i = 0; i < poolSize; i++) {
                Connection connection = DriverManager.getConnection(url, user, password);
                PooledConnection pooledConnection = new PooledConnection(connection);
                connectionQueue.add(pooledConnection);
            }
        } catch (SQLException e) {
            logger.error("Can't init connection pool data");
            throw new ConnectionPoolException(e);
        } catch (ClassNotFoundException e) {
            logger.error("Can't find database driver class", e);
            throw new ConnectionPoolException(e);
        }
    }

    public void dispose() { //
        clearConnectionQueue();
    }

    public void clearConnectionQueue() {
        try {
            closeConnectionsQueue(givenAwayConnectionQueue);
            closeConnectionsQueue(connectionQueue);
        } catch (SQLException e) {

            logger.error("Error closing the connection", e);
        }
    }

    public Connection takeConnection() throws ConnectionPoolException {
        Connection connection;
        try {
            connection = connectionQueue.take();
            givenAwayConnectionQueue.add(connection);
        } catch (InterruptedException e) {
            logger.error("Error connecting to the datasource", e);
            throw new ConnectionPoolException(e);
        }
        return connection;
    }


    public void closeConnection(Connection connection, Statement statement, ResultSet resultSet) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {

            logger.error("Connection isn't return to the pool", e);
        }

        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {

            logger.error("ResultSet isn't closed", e);
        }

        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            logger.error("Statement isn't closed", e);

        }
    }

    public void closeConnection(Connection connection, Statement statement) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            logger.error("Connection isn't return to the pool", e);
        }
        try {
            if (connection != null) {
                statement.close();
            }
        } catch (SQLException e) {
            logger.error("Statement isn't closed", e);
        }
    }

    public void closeConnection(Connection connection, ResultSet resultSet) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            logger.error("Connection isn't return to the pool", e);
        }
        try {
            if (connection != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            logger.error("Result set isn't closed", e);
        }
    }

    public void closeConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            logger.error("Connection isn't return to the pool", e);

        }
    }

    private void closeConnectionsQueue(BlockingQueue<Connection> queue) throws SQLException {
        Connection connection;

        while (((connection = queue.poll()) != null)) {
            if (!connection.getAutoCommit()) {
                connection.commit();
            }
            ((PooledConnection) connection).reallyClose();
        }
    }

    public void releaseConnection(Connection connection) throws SQLException {
        if (!givenAwayConnectionQueue.remove(connection)) {
            throw new SQLException("Error deleting connection from the given away connections pool");
        }

        if (!connectionQueue.offer(connection)) {
            throw new SQLException("Error allocating connection in the pool");
        }
    }
}
