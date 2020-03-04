package by.epam.controller.impl.hr;

import by.epam.bean.Message;
import by.epam.bean.User;
import by.epam.bean.Vacancy;
import by.epam.controller.Command;
import by.epam.controller.CommandName;
import by.epam.controller.CommandProvider;
import by.epam.exception.CommandException;
import by.epam.exception.ServiceException;
import org.apache.log4j.Logger;
import by.epam.service.MessageService;
import by.epam.service.ServiceFactory;
import by.epam.service.UserService;
import by.epam.service.VacancyService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AddVacancy implements Command {
    private static Logger log = Logger.getLogger(AddVacancy.class);
    private VacancyService vacancyService = ServiceFactory.getInstance().getVacancyService();
    private UserService userService = ServiceFactory.getInstance().getUserService();
    private MessageService messageService = ServiceFactory.getInstance().getMessageService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {

        try {
            User user = (User) req.getSession().getAttribute("user");

            String name = req.getParameter("vacancyName");
            String description = req.getParameter("description");
            Vacancy vacancy = new Vacancy(name, description);

            notifySeekers(user, name);
            vacancyService.create(vacancy);


            Command command = CommandProvider.getInstance().getCommand(CommandName.HOME_PAGE.name());

            command.execute(req, resp);

        } catch (ServiceException e) {
            log.error(e);
            throw new CommandException(e);
        }
    }

    private void notifySeekers(User user, String name) throws ServiceException {
        List<User> userList = userService.getAllSeekers();
        for (User value : userList) {
            int senderId = user.getId();
            int getterId = value.getId();
            String content = "Vacancy with name = " + name + " was added";
            Message message = new Message(senderId, getterId, content);
            messageService.create(message);
        }
    }
}
