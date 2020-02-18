package controller.impl.general;

import controller.Command;
import controller.CommandName;
import controller.CommandProvider;
import exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ChangeLanguage implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        HttpSession session = req.getSession();
        String language = req.getParameter("language");
        System.out.println(language);
        session.setAttribute("locale",language);
        Command command = CommandProvider.getInstance().getCommand(CommandName.MAIN_PAGE.name());
        command.execute(req, resp);
    }
}
