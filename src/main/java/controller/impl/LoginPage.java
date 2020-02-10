package controller.impl;

import bean.User;
import controller.Command;
import controller.CommandName;
import controller.CommandProvider;
import exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginPage implements Command {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {


        User user = (User) req.getAttribute("user");

        HttpSession session = req.getSession();
        session.setAttribute("user", user);

        Command command = CommandProvider.getInstance().getCommand(CommandName.PROFILE_PAGE.name());
        command.execute(req, resp);

    }


}
