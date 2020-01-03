package dao;

import dao.impl.UserDaoImpl;

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

    public abstract T create(String name , String password);

    public abstract T read (int index);

    public abstract T update (int index);

    public abstract T delete (int index);
}
