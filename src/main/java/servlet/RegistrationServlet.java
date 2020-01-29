package servlet;

import bean.User;
import dao.Dao;
import dao.impl.JdbcUserDao;
import service.ServiceFactory;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/userRegistration");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Dao<User> dao = new JdbcUserDao();
        UserService userService = ServiceFactory.getCalcService();


        String nameGET = req.getParameter("name");
        String passwordGET = req.getParameter("password");


        if (userService.isRegistered(nameGET)) {
            dao.create(new User(nameGET, passwordGET));
            resp.sendRedirect("/userLogin");
        } else {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/userRegistration");
            req.setAttribute("message", "This user has already registered");
            dispatcher.forward(req, resp);
        }
    }
}




