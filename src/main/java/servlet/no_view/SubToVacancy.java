package servlet.no_view;

import bean.User;
import bean.dto.VacResponded;
import dao.Dao;
import dao.impl.JdbcVacRespondedDao;
import servlet.PageAccess;
import servlet.view.MainServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/subVacancy")
public class SubToVacancy extends MainServlet implements PageAccess {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (isLogin(req) != null) {
            Dao<VacResponded> vacRespondedDao = new JdbcVacRespondedDao();
            int vacId = Integer.parseInt(req.getParameter("vacId"));
            int userId = Integer.parseInt(req.getParameter("userId"));

            vacRespondedDao.create(new VacResponded(userId, vacId));
            resp.sendRedirect("/home");
        } else {
            resp.sendRedirect("/login");
        }

    }

    @Override
    public User isLogin(HttpServletRequest request) {
        return checkLogin(request);
    }
}
