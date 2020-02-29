package controller.impl.hr;

import bean.Resume;
import bean.User;
import bean.Vacancy;
import controller.Command;
import exception.CommandException;
import exception.ServiceException;
import org.apache.log4j.Logger;
import service.ResumeService;
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
    private ResumeService resumeService = ServiceFactory.getInstance().getResumeService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        try {

            User hr = (User) req.getSession().getAttribute("user");
            int vacId = Integer.parseInt(req.getParameter("vacId"));
            int seekerId = Integer.parseInt(req.getParameter("seekerId"));
            System.out.println(vacId);
            System.out.println(seekerId);
            Resume resume = resumeService.readByUserIdVacancyId(seekerId,vacId);
            User seeker = userService.readById(seekerId);
            Vacancy vacancy = vacancyService.readById(vacId);

            req.setAttribute("seeker", seeker);
            req.setAttribute("vacancy", vacancy);
            req.setAttribute("hr", hr);
            req.setAttribute("resume",resume);
            req.getRequestDispatcher("/jsp/interviewPage.jsp").forward(req, resp);

        } catch (IOException | ServiceException | ServletException e) {
            log.error(e);
            throw new CommandException(e);
        }
    }


}
