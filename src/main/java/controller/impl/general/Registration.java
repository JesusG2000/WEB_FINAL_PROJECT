package controller.impl.general;

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
    private static Logger log = Logger.getLogger(Registration.class);
    private UserService userService = ServiceFactory.getInstance().getUserService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        String userName = req.getParameter("name");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");
        User user = new User(userName, password);
        try {
            if ((userName.length() < 4 && password.length() < 4) || (userName.length() > 16 && password.length() > 16)) {
                RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/registration.jsp");
                req.setAttribute("message", "Length might be from 4 to 16");
                dispatcher.forward(req, resp);

            } else {
                if (!password.equals(confirmPassword)) {
                    RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/registration.jsp");
                    req.setAttribute("message", "Passwords doesn't confirm");
                    dispatcher.forward(req, resp);
                } else {
                    if (!userService.isRegistered(user)) {
                        userService.create(user);
                        resp.sendRedirect("/jsp/login.jsp");
                    } else {
                        RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/registration.jsp");
                        req.setAttribute("message", "This user has already registered");
                        dispatcher.forward(req, resp);
                    }
                }
            }

        } catch (ServletException | IOException | ServiceException e) {
            log.error(e);
            throw new CommandException(e);
        }

    }
}
