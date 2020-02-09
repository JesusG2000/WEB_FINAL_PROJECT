package controller.impl;

import bean.Role;
import bean.User;
import controller.Command;
import controller.CommandName;
import controller.CommandProvider;
import controller.PageAccess;
import exception.CommandException;
import exception.ServiceException;
import service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteUser extends PageAccess implements Command {
    private UserService userService = ServiceFactory.getInstance().getUserService();
    private InterviewService interviewService = ServiceFactory.getInstance().getInterviewService();
    private MessageService messageService = ServiceFactory.getInstance().getMessageService();
    private VacRespondedService vacRespondedService = ServiceFactory.getInstance().getVacRespondedService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        try {
            //User user = isLogin(req);
            //if (user != null && user.getRole() == Role.ADMIN) {
                User deleteUser = new User(Integer.parseInt(req.getParameter("id")));

                interviewService.deleteAllInterviewBySeeker(deleteUser);
                messageService.deleteAllMessageByUser(deleteUser);
                vacRespondedService.deleteAllVacRespondedBySeeker(deleteUser);

                userService.deleteById(deleteUser.getId());
                Command command = CommandProvider.getInstance().getCommand(CommandName.ADMIN_HOME_PAGE.name());
                command.execute(req, resp);

          //  } else {
            //    resp.sendRedirect("/login.jsp");
           // }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }

    @Override
    public User isLogin(HttpServletRequest request) {
        return checkLogin(request);
    }
}
