package servlet.view;

import bean.User;
import dao.InterviewDao;
import dao.impl.JdbcInterviewDao;
import servlet.PageAccess;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/profile")
public class ProfileServlet extends MainServlet implements PageAccess {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = isLogin(req);
        if(user!=null) {
            InterviewDao interviewDao = new JdbcInterviewDao();
            req.setAttribute("interview", interviewDao.getAllInterviewBySeekerId(user));
            req.getRequestDispatcher("/profile.jsp").forward(req, resp);
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
