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
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        Command command = CommandProvider.getInstance().getCommand(CommandName.RESUME_PAGE.name());
        command.execute(req, resp);

    }
}
