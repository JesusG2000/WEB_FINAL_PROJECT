package controller.impl.general;

import controller.Command;
import exception.CommandException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
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
