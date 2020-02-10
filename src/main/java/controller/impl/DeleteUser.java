package controller.impl;

import bean.User;
import controller.Command;
import controller.CommandName;
import controller.CommandProvider;
import exception.CommandException;
import exception.ServiceException;
import org.apache.log4j.Logger;
import service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteUser implements Command {
    private static Logger log = Logger.getLogger(DeleteUser.class);
    private UserService userService = ServiceFactory.getInstance().getUserService();
    private InterviewService interviewService = ServiceFactory.getInstance().getInterviewService();
    private MessageService messageService = ServiceFactory.getInstance().getMessageService();
    private VacRespondedService vacRespondedService = ServiceFactory.getInstance().getVacRespondedService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        try {

            User deleteUser = new User(Integer.parseInt(req.getParameter("id")));

            interviewService.deleteAllInterviewBySeeker(deleteUser);
            messageService.deleteAllMessageByUser(deleteUser);
            vacRespondedService.deleteAllVacRespondedBySeeker(deleteUser);

            userService.deleteById(deleteUser.getId());
            Command command = CommandProvider.getInstance().getCommand(CommandName.ADMIN_HOME_PAGE.name());
            command.execute(req, resp);


        } catch (ServiceException e) {
            log.error(e);
            throw new CommandException(e);
        }
    }


}
