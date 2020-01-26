package servlet;

import dao.VacancyDao;
import dao.impl.JdbcVacancyDao;
import org.w3c.dom.ls.LSOutput;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        VacancyDao vacancyDao = new JdbcVacancyDao();

        RequestDispatcher dispatcher = req.getRequestDispatcher("/home.jsp");
        req.setAttribute("vacancies" , vacancyDao.getAllVacancy());
        dispatcher.forward(req,resp);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
