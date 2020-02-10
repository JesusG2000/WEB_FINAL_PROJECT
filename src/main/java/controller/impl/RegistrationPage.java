package controller.impl;

import controller.Command;
import exception.CommandException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationPage implements Command {
    private static Logger log = Logger.getLogger(RegistrationPage.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        try {

            resp.sendRedirect("/registration.jsp");

        } catch (IOException e) {
            log.error(e);
            throw new CommandException(e);
        }
    }


}
