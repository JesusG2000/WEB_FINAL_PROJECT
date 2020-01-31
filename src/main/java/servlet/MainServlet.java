package servlet;

import bean.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public abstract class MainServlet extends HttpServlet {
    public  User checkLogin(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (User) session.getAttribute("user");
    }

    public abstract User isLogin(HttpServletRequest request);
}
