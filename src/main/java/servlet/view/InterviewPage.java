package servlet.view;

import bean.Interview;
import bean.User;
import bean.Vacancy;
import dao.Dao;
import dao.impl.JdbcInterviewDao;
import dao.impl.JdbcUserDao;
import dao.impl.JdbcVacancyDao;
import servlet.PageAccess;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

@WebServlet("/submitInterview")
public class InterviewPage extends MainServlet implements PageAccess {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (isLogin(req) != null) {

            Dao<User> userDao = new JdbcUserDao();
            Dao<Vacancy> vacancyDao = new JdbcVacancyDao();

            int vacId = Integer.parseInt(req.getParameter("vacId"));
            int seekerId = Integer.parseInt(req.getParameter("seekerId"));

            User seeker = new User(seekerId);
            Vacancy vacancy = new Vacancy(vacId);
            User hr = (User) req.getSession().getAttribute("user");

            seeker = userDao.read(seeker);
            vacancy = vacancyDao.read(vacancy);

            req.setAttribute("seeker", seeker);
            req.setAttribute("vacancy", vacancy);
            req.setAttribute("hr", hr);

            req.getRequestDispatcher("/interviewPage.jsp").forward(req, resp);


        } else {
            resp.sendRedirect("/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Dao<Interview> dao = new JdbcInterviewDao();

        int hrId = Integer.parseInt(req.getParameter("hrId"));
        int seekerId = Integer.parseInt(req.getParameter("seekerId"));
        Date date = Date.valueOf(req.getParameter("date"));
        String comment = req.getParameter("comment");
        int vacId = Integer.parseInt(req.getParameter("vacancyId"));

        Interview interview = new Interview(hrId, seekerId ,vacId, date, comment);
        dao.create(interview);

        String redirectUrl = "/updateVacancy?id=" + vacId;
        resp.sendRedirect(redirectUrl);


    }

    @Override
    public User isLogin(HttpServletRequest request) {
        return checkLogin(request);
    }
}
