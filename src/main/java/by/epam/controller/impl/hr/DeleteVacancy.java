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

public class DeleteVacancy implements Command {
    private static Logger log = Logger.getLogger(DeleteVacancy.class);
    private VacancyService vacancyService = ServiceFactory.getInstance().getVacancyService();
    private UserService userService = ServiceFactory.getInstance().getUserService();
    private MessageService messageService = ServiceFactory.getInstance().getMessageService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        try {
            User user = (User) req.getSession().getAttribute("user");
            if (req.getParameter("id") != null) {
                int vacancyId = Integer.parseInt(req.getParameter("id"));

                notifySeekers(user, vacancyId);
                vacancyService.deleteById(vacancyId);

            }
            Command command = CommandProvider.getInstance().getCommand(CommandName.HOME_PAGE.name());
            command.execute(req, resp);

        } catch (ServiceException | NullPointerException e) {
            log.error(e);
            throw new CommandException(e);
        }
    }

    private void notifySeekers(User user, int vacancyId) throws ServiceException {
        Vacancy vacancy = vacancyService.readById(vacancyId);
        if (vacancy != null) {
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
}
