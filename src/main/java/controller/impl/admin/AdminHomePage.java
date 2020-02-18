package controller.impl.admin;

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

public class AdminHomePage implements Command {
    private static Logger log = Logger.getLogger(AdminHomePage.class);
    private UserService userService = ServiceFactory.getInstance().getUserService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        try {

            RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/adminHome.jsp");
            req.setAttribute("seekers", userService.getAllSeekers());
            req.setAttribute("hrs", userService.getAllHR());
            dispatcher.forward(req, resp);

        } catch (IOException | ServletException | ServiceException e) {
            log.error(e);
            throw new CommandException(e);
        }
    }
}
