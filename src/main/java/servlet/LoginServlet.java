package servlet;

import dao.impl.UserDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/userLogin");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = UserDaoImpl.getConnection();
        String username = req.getParameter("name");
        String password = req.getParameter("password");
        try {
            if(isLogin(username,password,connection.createStatement())){
                resp.sendRedirect("/welcome");
            }else{
                resp.sendRedirect("/userLogin");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    private boolean isLogin(String username, String password, Statement statement) throws SQLException {
        String query = "select name , password from users";
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            if (resultSet.getString(1).equals(username)&&resultSet.getString(2).equals(password)) {
                return true;
            }
        }
        return false;
    }
}
