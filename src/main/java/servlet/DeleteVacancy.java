package servlet;

import bean.Vacancy;
import dao.Dao;
import dao.impl.JdbcVacancyDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/deleteVacancy")
public class DeleteVacancy extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Dao<Vacancy> dao = new JdbcVacancyDao();
        Vacancy vacancy =new Vacancy(Integer.parseInt(req.getParameter("id")));
        System.out.println(vacancy);
        dao.delete(vacancy);
        resp.sendRedirect("/home");
    }
}
