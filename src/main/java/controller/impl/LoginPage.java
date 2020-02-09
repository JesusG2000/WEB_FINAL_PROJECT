package controller.impl;

import bean.User;
import controller.Command;
import controller.CommandName;
import controller.CommandProvider;
import controller.PageAccess;
import exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginPage extends PageAccess implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        try {
            HttpSession session = req.getSession();
            User user = isLogin(req);
            if (user != null) {
                session.setAttribute("user", user);

                Command command = CommandProvider.getInstance().getCommand(CommandName.PROFILE_PAGE.name());
                command.execute(req, resp);
            } else {
                resp.sendRedirect("/login.jsp");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User isLogin(HttpServletRequest request) {
        return checkLogin(request);
    }
}
