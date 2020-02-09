package controller.impl;

import bean.Role;
import bean.User;
import bean.VacResponded;
import controller.Command;
import controller.CommandName;
import controller.CommandProvider;
import controller.PageAccess;
import exception.CommandException;
import exception.ServiceException;
import service.ServiceFactory;
import service.VacRespondedService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SubscribeToVacancy extends PageAccess implements Command {
    private VacRespondedService vacRespondedService = ServiceFactory.getInstance().getVacRespondedService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        try {
            User user = isLogin(req);
            if (user != null && user.getRole() == Role.SEEKER) {

                int vacId = Integer.parseInt(req.getParameter("vacId"));
                int userId = Integer.parseInt(req.getParameter("userId"));

                vacRespondedService.create(new VacResponded(userId, vacId));
                Command command = CommandProvider.getInstance().getCommand(CommandName.HOME_PAGE.name());
                command.execute(req, resp);
            } else {
                resp.sendRedirect("/login.jsp");
            }
        } catch (IOException | ServiceException e) {
           throw new CommandException(e);
        }
    }

    @Override
    public User isLogin(HttpServletRequest request) {
        return checkLogin(request);
    }
}
