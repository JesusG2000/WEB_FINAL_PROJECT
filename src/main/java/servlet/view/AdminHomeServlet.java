package servlet.view;

import bean.User;
import dao.UserDao;
import dao.impl.JdbcUserDao;
import servlet.PageAccess;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/adminHome")
public class AdminHomeServlet extends MainServlet implements PageAccess {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(isLogin(req)!=null) {
            UserDao userDao = new JdbcUserDao();
            System.out.println( userDao.getAllSeekers());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/adminHome.jsp");
            req.setAttribute("seekers", userDao.getAllSeekers());
            req.setAttribute("hrs", userDao.getAllHR());
            dispatcher.forward(req, resp);
        }else{
            resp.sendRedirect("/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
    @Override
    public User isLogin(HttpServletRequest request) {
        return checkLogin(request);
    }
}
