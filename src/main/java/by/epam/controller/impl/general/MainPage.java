package by.epam.controller.impl.general;

import by.epam.bean.User;
import by.epam.controller.Command;
import by.epam.controller.CommandName;
import by.epam.controller.CommandProvider;
import by.epam.exception.CommandException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MainPage implements Command {
    private static Logger log = Logger.getLogger(MainPage.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        try {
            HttpSession session = req.getSession();
            User user = (User) session.getAttribute("user");
            Command command;
            if (user != null) {
                command = CommandProvider.getInstance().getCommand(CommandName.PROFILE_PAGE.name());

            } else {
                command = CommandProvider.getInstance().getCommand(CommandName.LOGIN_PAGE.name());
            }
            command.execute(req, resp);
        } catch (NullPointerException e) {
            log.error(e);
            throw new CommandException("Redirect to page error", e);
        }
    }
}
