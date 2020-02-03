package servlet.view;

import bean.User;
import bean.Vacancy;
import dao.Dao;
import dao.impl.JdbcVacancyDao;
import servlet.PageAccess;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/addVacancy")
public class AddVacancyServlet extends MainServlet implements PageAccess {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/addVacancy.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Dao<Vacancy> vacancyDao = new JdbcVacancyDao();
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        if(vacancyDao.create(new Vacancy(name,description))){
            resp.sendRedirect("/home");
        }
    }

    @Override
    public User isLogin(HttpServletRequest request) {
        return checkLogin(request);
    }
}
