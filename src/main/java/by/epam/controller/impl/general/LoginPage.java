package by.epam.controller.impl.general;

import by.epam.controller.Command;
import by.epam.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginPage implements Command {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {

        try {
            resp.sendRedirect("/jsp/login.jsp");
        } catch (IOException e) {
            throw new CommandException(e);
        }
    }
}
