package controller.impl;

import bean.User;
import bean.Vacancy;
import controller.Command;
import exception.CommandException;
import exception.ServiceException;
import org.apache.log4j.Logger;
import service.ServiceFactory;
import service.UserService;
import service.VacancyService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class InterviewPage implements Command {
    private static Logger log = Logger.getLogger(InterviewPage.class);
    private UserService userService = ServiceFactory.getInstance().getUserService();
    private VacancyService vacancyService = ServiceFactory.getInstance().getVacancyService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        try {


            int vacId = Integer.parseInt(req.getParameter("vacId"));
            int seekerId = Integer.parseInt(req.getParameter("seekerId"));

            User seeker = new User(seekerId);
            Vacancy vacancy = new Vacancy(vacId);
            User hr = (User) req.getSession().getAttribute("user");

            seeker = userService.readById(seeker.getId());
            vacancy = vacancyService.readById(vacancy.getId());

            req.setAttribute("seeker", seeker);
            req.setAttribute("vacancy", vacancy);
            req.setAttribute("hr", hr);
            req.getRequestDispatcher("/interviewPage.jsp").forward(req, resp);

        } catch (IOException | ServiceException | ServletException e) {
            log.error(e);
            throw new CommandException(e);
        }
    }


}
