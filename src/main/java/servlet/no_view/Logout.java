package servlet.no_view;

import bean.User;
import servlet.PageAccess;
import servlet.view.MainServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebServlet("/logout")
public class Logout extends MainServlet implements PageAccess {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(isLogin(req)!=null) {
            HttpSession session = req.getSession();
            session.removeAttribute("user");
            session.removeAttribute("interview");
            resp.sendRedirect("/login");
        }else {
            resp.sendRedirect("/userLogin");
        }
    }

    @Override
    public User isLogin(HttpServletRequest request) {
        return checkLogin(request);
    }
}
