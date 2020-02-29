package controller.impl.general;

import bean.User;
import controller.Command;
import exception.CommandException;
import exception.ServiceException;
import org.apache.log4j.Logger;
import service.ServiceFactory;
import service.VacancyService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.CompletionException;

public class HomePage implements Command {
    private static Logger log = Logger.getLogger(HomePage.class);
    private VacancyService vacancyService = ServiceFactory.getInstance().getVacancyService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        try {
            User user = (User)  req.getSession().getAttribute("user");

            RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/home.jsp");
            req.setAttribute("currentUser", user);
            req.setAttribute("vacancies", vacancyService.getAllVacancy());
            dispatcher.forward(req, resp);

        } catch (IOException | ServletException | ServiceException e) {
            log.error(e);
            throw new CompletionException(e);
        }
    }


}
