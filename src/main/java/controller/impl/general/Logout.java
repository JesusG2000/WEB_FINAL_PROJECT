package controller.impl.general;

import controller.Command;
import exception.CommandException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Logout implements Command {
    private static Logger log = Logger.getLogger(Logout.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        try {
            HttpSession session = req.getSession();
            session.removeAttribute("user");
            resp.sendRedirect("/jsp/login.jsp");
        } catch (IOException e) {
            log.error(e);
            throw new CommandException(e);
        }
    }
}