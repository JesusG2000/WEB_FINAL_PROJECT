package by.epam.controller.impl.general;

import by.epam.controller.Command;
import by.epam.controller.CommandName;
import by.epam.controller.CommandProvider;
import by.epam.exception.CommandException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Logout implements Command {
    private static Logger log = Logger.getLogger(Logout.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {

        HttpSession session = req.getSession();
        session.removeAttribute("user");
        Command command = CommandProvider.getInstance().getCommand(CommandName.LOGIN_PAGE.name());
        command.execute(req, resp);

    }
}
