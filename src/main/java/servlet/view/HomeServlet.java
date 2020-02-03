package servlet.view;

import bean.User;
import dao.VacancyDao;
import dao.impl.JdbcVacancyDao;
import servlet.PageAccess;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/home")
public class HomeServlet extends MainServlet implements PageAccess {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = isLogin(req);
        if (user != null) {
            VacancyDao vacancyDao = new JdbcVacancyDao();
            RequestDispatcher dispatcher = req.getRequestDispatcher("/home.jsp");
            req.setAttribute("currentUser", user);
            req.setAttribute("vacancies", vacancyDao.getAllVacancy());
            dispatcher.forward(req, resp);
        } else {
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
