package controller.impl.seeker;

import bean.VacResponded;
import controller.Command;
import controller.CommandName;
import controller.CommandProvider;
import exception.CommandException;
import exception.ServiceException;
import org.apache.log4j.Logger;
import service.ServiceFactory;
import service.VacRespondedService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SubscribeToVacancy  implements Command {
    private VacRespondedService vacRespondedService = ServiceFactory.getInstance().getVacRespondedService();
    private static Logger log = Logger.getLogger(SubscribeToVacancy.class);
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        try {
                int vacId = Integer.parseInt(req.getParameter("vacId"));
                int userId = Integer.parseInt(req.getParameter("userId"));

                vacRespondedService.create(new VacResponded(userId, vacId));
                Command command = CommandProvider.getInstance().getCommand(CommandName.HOME_PAGE.name());
                command.execute(req, resp);

        } catch (ServiceException e) {
            log.error(e);
           throw new CommandException(e);
        }
    }
}
