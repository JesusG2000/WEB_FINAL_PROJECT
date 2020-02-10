package controller.impl;

import controller.Command;
import exception.CommandException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ErrorPage implements Command {
    private static Logger log = Logger.getLogger(ErrorPage.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        try {
            resp.sendRedirect("/error.jsp");
        } catch (IOException e) {
            log.error(e);
            throw new CommandException(e);
        }
    }
}
