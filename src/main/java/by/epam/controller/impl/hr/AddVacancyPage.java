package by.epam.controller.impl.hr;

import by.epam.controller.Command;
import by.epam.exception.CommandException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddVacancyPage implements Command {
    private static Logger log = Logger.getLogger(AddVacancyPage.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        try {

            req.getRequestDispatcher("/jsp/addVacancy.jsp").forward(req, resp);

        } catch (IOException | ServletException e) {
            log.error(e);
            throw new CommandException(e);
        }
    }
}
