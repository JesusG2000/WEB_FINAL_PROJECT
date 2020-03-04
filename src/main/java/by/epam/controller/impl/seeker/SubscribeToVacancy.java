package by.epam.controller.impl.seeker;

import by.epam.controller.Command;
import by.epam.controller.CommandName;
import by.epam.controller.CommandProvider;
import by.epam.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SubscribeToVacancy implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        Command command = CommandProvider.getInstance().getCommand(CommandName.RESUME_PAGE.name());
        command.execute(req, resp);

    }
}
