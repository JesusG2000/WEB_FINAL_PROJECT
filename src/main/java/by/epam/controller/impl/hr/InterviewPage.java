package by.epam.controller.impl.hr;

import by.epam.bean.Resume;
import by.epam.bean.User;
import by.epam.bean.Vacancy;
import by.epam.controller.Command;
import by.epam.exception.CommandException;
import by.epam.exception.ServiceException;
import org.apache.log4j.Logger;
import by.epam.service.ResumeService;
import by.epam.service.ServiceFactory;
import by.epam.service.UserService;
import by.epam.service.VacancyService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class InterviewPage implements Command {
    private static Logger log = Logger.getLogger(InterviewPage.class);
    private UserService userService = ServiceFactory.getInstance().getUserService();
    private VacancyService vacancyService = ServiceFactory.getInstance().getVacancyService();
    private ResumeService resumeService = ServiceFactory.getInstance().getResumeService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        try {

            User hr = (User) req.getSession().getAttribute("user");
            int vacId = Integer.parseInt(req.getParameter("vacId"));
            int seekerId = Integer.parseInt(req.getParameter("seekerId"));
            System.out.println(vacId);
            System.out.println(seekerId);
            Resume resume = resumeService.readByUserIdVacancyId(seekerId, vacId);
            User seeker = userService.readById(seekerId);
            Vacancy vacancy = vacancyService.readById(vacId);

            req.setAttribute("seeker", seeker);
            req.setAttribute("vacancy", vacancy);
            req.setAttribute("hr", hr);
            req.setAttribute("resume", resume);
            req.getRequestDispatcher("/jsp/interviewPage.jsp").forward(req, resp);

        } catch (IOException | ServiceException | ServletException e) {
            log.error(e);
            throw new CommandException(e);
        }
    }


}
