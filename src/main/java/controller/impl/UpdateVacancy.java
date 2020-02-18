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

public class UpdateVacancy  implements Command {
    private VacancyService vacancyService = ServiceFactory.getInstance().getVacancyService();
    private UserService userService = ServiceFactory.getInstance().getUserService();
    private MessageService messageService = ServiceFactory.getInstance().getMessageService();
    private static Logger log = Logger.getLogger(UpdateVacancy.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {

        try {
            User user = (User) (User) req.getSession().getAttribute("user");

                int id = Integer.parseInt(req.getParameter("id"));
                String name = req.getParameter("name");
                String description = req.getParameter("description");

                Vacancy vacancy = new Vacancy(id, name, description);
                notifySeekers(user , id);
                vacancyService.update(vacancy);

                Command command = CommandProvider.getInstance().getCommand(CommandName.HOME_PAGE.name());
                command.execute(req, resp);

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
            String content = "Vacancy with id = "+vacancyId+" , name = " + vacancy.getName() + " was updated ";
            Message message = new Message(senderId, getterId, content);
            messageService.create(message);
        }
    }
}
