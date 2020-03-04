package by.epam.controller.impl.admin;

import by.epam.bean.User;
import by.epam.controller.Command;
import by.epam.controller.CommandName;
import by.epam.controller.CommandProvider;
import by.epam.exception.CommandException;
import by.epam.exception.ServiceException;
import org.apache.log4j.Logger;
import by.epam.service.*;

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

            Command command = CommandProvider.getInstance().getCommand(CommandName.PROFILE_PAGE.name());
            command.execute(req, resp);


        } catch (ServiceException e) {
            log.error(e);
            throw new CommandException(e);
        }
    }


}
