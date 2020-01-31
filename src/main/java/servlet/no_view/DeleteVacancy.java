package servlet.no_view;

import bean.User;
import bean.Vacancy;
import dao.Dao;
import dao.impl.JdbcVacancyDao;
import servlet.MainServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/deleteVacancy")
public class DeleteVacancy extends MainServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Dao<Vacancy> dao = new JdbcVacancyDao();
        Vacancy vacancy =new Vacancy(Integer.parseInt(req.getParameter("id")));
        dao.delete(vacancy);
        resp.sendRedirect("/home");
    }
    @Override
    public User isLogin(HttpServletRequest request) {
        return null;
    }
}
