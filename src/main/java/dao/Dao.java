package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class Dao<T> {
    private static Connection connection;
    static {
        String database = "jdbc:mysql://localhost:3306/final_project?serverTimezone=UTC&useSSL=false";
        String username = "raf";
        String password = "dfyjl;fy";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(database, username, password);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection() {
        return connection;
    }

    public static void setConnection(Connection connection) {
        Dao.connection = connection;
    }

    public abstract boolean create(T t);

    public abstract T read (T t);

    public abstract T update (T t);

    public abstract T delete (T t);
}
