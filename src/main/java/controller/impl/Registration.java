package controller.impl;

import bean.User;
import controller.Command;
import exception.CommandException;
import exception.ServiceException;
import org.apache.log4j.Logger;
import service.ServiceFactory;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Registration implements Command {
    private UserService userService = ServiceFactory.getInstance().getUserService();
    private static Logger log = Logger.getLogger(Registration.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        String nameGET = req.getParameter("name");
        String passwordGET = req.getParameter("password");
        User user = new User(nameGET, passwordGET);
        try {
            if (!userService.isRegistered(user)) {
                userService.create(user);
                resp.sendRedirect("/login.jsp");
            } else {
                RequestDispatcher dispatcher = req.getRequestDispatcher("/registration.jsp");
                req.setAttribute("message", "This user has already registered");

                dispatcher.forward(req, resp);
            }
        } catch (ServletException | IOException | ServiceException e) {
            log.error(e);
           throw new CommandException(e);
        }

    }
}
