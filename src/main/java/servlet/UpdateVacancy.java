package servlet;

import bean.Vacancy;
import dao.Dao;
import dao.impl.JdbcVacancyDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/updateVacancy")
public class UpdateVacancy extends HomeServlet {
    private Dao<Vacancy> dao = new JdbcVacancyDao();

    @Override
    public void init() throws ServletException {
        dao = new JdbcVacancyDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Vacancy vacancy = dao.read(new Vacancy(Integer.parseInt(req.getParameter("id"))));
        System.out.println(vacancy);
        req.setAttribute("vacancy", vacancy);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/vacancyInfo.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String description = req.getParameter("description");

        dao.update(new Vacancy(id, name, description));
        resp.sendRedirect("/home");

    }
}
