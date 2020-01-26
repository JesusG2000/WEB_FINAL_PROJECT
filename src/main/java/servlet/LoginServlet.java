package servlet;

import dao.UserDao;
import dao.impl.JdbcUserDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/userLogin");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDao userDao = new JdbcUserDao();
        String username = req.getParameter("name");
        String password = req.getParameter("password");

        RequestDispatcher dispatcher;
        if (userDao.isLogin(username, password)) {
            dispatcher = req.getRequestDispatcher("/welcome");
            req.setAttribute("user", userDao.getUserByName(username));
        } else {
            dispatcher = req.getRequestDispatcher("/userLogin");
            req.setAttribute("message", "Not correct login or password");
        }
        dispatcher.forward(req, resp);


    }
}
