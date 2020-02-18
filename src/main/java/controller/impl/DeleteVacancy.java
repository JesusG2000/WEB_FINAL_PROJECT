package controller.impl;

import bean.Message;
import bean.User;
import bean.Vacancy;
import controller.Command;
import controller.CommandName;
import controller.CommandProvider;
import exception.CommandException;
import exception.ServiceException;
import org.apache.log4j.Logger;
import service.MessageService;
import service.ServiceFactory;
import service.UserService;
import service.VacancyService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class DeleteVacancy implements Command {
    private static Logger log = Logger.getLogger(DeleteVacancy.class);
    private VacancyService vacancyService = ServiceFactory.getInstance().getVacancyService();
    private UserService userService = ServiceFactory.getInstance().getUserService();
    private MessageService messageService = ServiceFactory.getInstance().getMessageService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        try {
            User user = (User) (User) req.getSession().getAttribute("user");

            int vacancyId = Integer.parseInt(req.getParameter("id"));

            notifySeekers(user, vacancyId);
            vacancyService.deleteById(vacancyId);

            Command command = CommandProvider.getInstance().getCommand(CommandName.HOME_PAGE.name());
            command.execute(req,resp);

        } catch (ServiceException e) {
            log.error(e);
            throw new CommandException(e);
        }
    }

    private void notifySeekers(User user, int vacancyId) throws ServiceException {
        Vacancy vacancy = vacancyService.readById(vacancyId);
        List<User> userList = userService.getAllSeekers();
        for (User value : userList) {
            int senderId = user.getId();
            int getterId = value.getId();
            String content = "Vacancy with name = " + vacancy.getName() + " was deleted";
            Message message = new Message(senderId, getterId, content);
            messageService.create(message);
        }
    }


}
