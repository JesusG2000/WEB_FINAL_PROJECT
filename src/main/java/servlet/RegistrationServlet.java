package servlet;

import bean.User;
import dao.Dao;
import dao.impl.UserDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    private List<User> users;

    @Override
    public void init() throws ServletException {
        users = new ArrayList<>();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/userRegistration");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Dao<User> userDao = new UserDaoImpl();
        Connection connection = UserDaoImpl.getConnection();


        String nameGET = req.getParameter("name");
        String passwordGET = req.getParameter("password");

        try {
            if (checkNameDuplicate(nameGET, connection.createStatement())) {
                String sqlInsertUser = "INSERT INTO users (name, password) values (?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sqlInsertUser);
                preparedStatement.setString(1, nameGET);
                preparedStatement.setString(2, passwordGET);

                System.out.println("Prepared Statement: " + preparedStatement);
                users.add(userDao.create(nameGET, passwordGET));
                preparedStatement.executeUpdate();


                resp.sendRedirect("/userLogin");
            }else{
                resp.sendRedirect("/registration");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean checkNameDuplicate(String nameGET, Statement statement) throws SQLException {
        String query = "select name from users";
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            if (resultSet.getString(1).equals(nameGET)) {
                return false;
            }
        }
        return true;
    }
}
