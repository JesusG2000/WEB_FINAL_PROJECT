package controller.impl.hr;

import bean.Vacancy;
import controller.Command;
import exception.CommandException;
import exception.ServiceException;
import org.apache.log4j.Logger;
import service.ServiceFactory;
import service.UserService;
import service.VacancyService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateVacancyPage  implements Command {
    private VacancyService vacancyService = ServiceFactory.getInstance().getVacancyService();
    private UserService userService = ServiceFactory.getInstance().getUserService();
    private static Logger log = Logger.getLogger(UpdateVacancyPage.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        try {
                Vacancy vacancy = vacancyService.readById(Integer.parseInt(req.getParameter("id")));
                req.setAttribute("vacancy", vacancy);
                req.setAttribute("seekers", userService.getAllByVacancy(vacancy));
                RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/vacancyInfo.jsp");
                dispatcher.forward(req, resp);

        } catch (IOException | ServiceException | ServletException e) {
            log.error(e);
            throw new CommandException(e);
        }
    }


}
