package servlet;

import bean.User;
import dao.Dao;
import dao.impl.JdbcUserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteUser")
public class DeleteUser extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Dao<User> dao = new JdbcUserDao();
        User user = new User(Integer.parseInt(req.getParameter("id")));
        dao.delete(user);
        resp.sendRedirect("/adminHome");
    }
}
