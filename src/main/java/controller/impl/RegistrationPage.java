package controller.impl;

import bean.User;
import controller.Command;
import controller.PageAccess;
import exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationPage extends PageAccess implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        try {
            // if (isLogin(req) != null) {
//                Command command = CommandProvider.getInstance().getCommand(CommandName.PROFILE_PAGE.name());
//                command.execute(req, resp);
            //   } else {
            resp.sendRedirect("/registration.jsp");
            // }
        } catch (IOException e) {
            throw new CommandException(e);
        }
    }

    @Override
    public User isLogin(HttpServletRequest request) {
        return checkLogin(request);
    }
}
