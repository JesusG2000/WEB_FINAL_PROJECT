package servlet;

import bean.User;
import bean.Vacancy;
import dao.Dao;
import dao.UserDao;
import dao.impl.JdbcUserDao;
import dao.impl.JdbcVacancyDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/updateVacancy")
public class UpdateVacancy extends MainServlet {
    private Dao<Vacancy> dao;

    @Override
    public void init() throws ServletException {
        dao = new JdbcVacancyDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(isLogin(req)!=null) {
            UserDao userDao = new JdbcUserDao();
            Vacancy vacancy = dao.read(new Vacancy(Integer.parseInt(req.getParameter("id"))));
            req.setAttribute("vacancy", vacancy);
            req.setAttribute("users", userDao.getAllByVacancyId(vacancy));
            RequestDispatcher dispatcher = req.getRequestDispatcher("/vacancyInfo.jsp");
            dispatcher.forward(req, resp);
        }else{
            resp.sendRedirect("/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String description = req.getParameter("description");

        dao.update(new Vacancy(id, name, description));
        resp.sendRedirect("/home");

    }
    @Override
    public User isLogin(HttpServletRequest request) {
        return checkLogin(request);
    }
}
