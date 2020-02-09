package controller.impl;

import bean.User;
import controller.Command;
import controller.PageAccess;
import exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class MainPage extends PageAccess implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        try {
            HttpSession session = req.getSession();
            User user = isLogin(req);
            if (user != null) {
                session.setAttribute("user", user);

                resp.sendRedirect("/profile.jsp");
            } else {
                resp.sendRedirect("/login.jsp");
            }
        } catch (IOException e) {
            throw new CommandException("Redirect to page error" , e);
        }
    }


    @Override
    public User isLogin(HttpServletRequest request) {
        return checkLogin(request);
    }
}
