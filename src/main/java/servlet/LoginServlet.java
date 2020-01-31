package servlet;

import bean.User;
import dao.UserDao;
import dao.impl.JdbcUserDao;
import service.ServiceFactory;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends MainServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        User user = isLogin(req);
            if (user != null) {
            session.setAttribute("user", user);
            req.getRequestDispatcher("/welcome").forward(req, resp);
        }
        resp.sendRedirect("/userLogin");


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDao userDao = new JdbcUserDao();
        UserService userService = ServiceFactory.getCalcService();
        HttpSession session = req.getSession();


        RequestDispatcher dispatcher;

        String username = req.getParameter("name");
        String password = req.getParameter("password");


        if (userService.isExist(username, password)) {
            session.setAttribute("user", userDao.getUserByName(username));
            dispatcher = req.getRequestDispatcher("/welcome");
        } else {
            dispatcher = req.getRequestDispatcher("/userLogin");
            req.setAttribute("message", "Not correct login or password");
        }
        dispatcher.forward(req, resp);


    }
    @Override
    public User isLogin(HttpServletRequest request) {
       return checkLogin(request);
    }
}
