package servlet.no_view;

import bean.User;
import dao.Dao;
import dao.impl.JdbcUserDao;
import servlet.PageAccess;
import servlet.view.MainServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteUser")
public class DeleteUser extends MainServlet implements PageAccess {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (isLogin(req) != null) {
            Dao<User> dao = new JdbcUserDao();
            User user = new User(Integer.parseInt(req.getParameter("id")));
            dao.delete(user);
            resp.sendRedirect("/adminHome");
        } else {
            resp.sendRedirect("/login");
        }
    }

    @Override
    public User isLogin(HttpServletRequest request) {
        return checkLogin(request);
    }
}
