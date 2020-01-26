package servlet;

import bean.User;
import dao.Dao;
import dao.UserDao;
import dao.impl.JdbcUserDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/userRegistration");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDao userDao = new JdbcUserDao();
        Dao<User> dao = new JdbcUserDao();


        String nameGET = req.getParameter("name");
        String passwordGET = req.getParameter("password");


        if (userDao.checkNameDuplicate(nameGET)) {
            dao.create(new User(nameGET, passwordGET));
            resp.sendRedirect("/userLogin");
        } else {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/userRegistration");
            req.setAttribute("message", "This user has already registered");
            dispatcher.forward(req, resp);
        }
    }
}



