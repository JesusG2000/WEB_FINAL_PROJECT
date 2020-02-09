package controller.impl;

import controller.Command;
import exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Logout implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        try {
            HttpSession session = req.getSession();
            session.removeAttribute("user");
            resp.sendRedirect("/login.jsp");
        } catch (IOException e) {
            throw new CommandException(e);
        }
    }
}
