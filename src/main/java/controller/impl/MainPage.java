package controller.impl;

import bean.User;
import controller.Command;
import controller.CommandName;
import controller.CommandProvider;
import exception.CommandException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class MainPage implements Command {
    private static Logger log = Logger.getLogger(MainPage.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        try {
            HttpSession session = req.getSession();
            User user = (User)  req.getSession().getAttribute("user");
            if (user != null) {

                Command command = CommandProvider.getInstance().getCommand(CommandName.PROFILE_PAGE.name());
                command.execute(req, resp);
            } else {
                resp.sendRedirect("/login.jsp");
            }
        } catch (IOException e) {
            log.error(e);
            throw new CommandException("Redirect to page error", e);
        }
    }
}
