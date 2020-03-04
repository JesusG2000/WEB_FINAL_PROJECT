package by.epam.controller.impl.seeker;

import by.epam.bean.Resume;
import by.epam.bean.VacResponded;
import by.epam.controller.Command;
import by.epam.controller.CommandName;
import by.epam.controller.CommandProvider;
import by.epam.exception.CommandException;
import by.epam.exception.ServiceException;
import by.epam.service.ResumeService;
import by.epam.service.ServiceFactory;
import by.epam.service.VacRespondedService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SubmitResume implements Command {
    private ResumeService resumeService = ServiceFactory.getInstance().getResumeService();
    private VacRespondedService vacRespondedService = ServiceFactory.getInstance().getVacRespondedService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        try {
            int userId = Integer.parseInt(req.getParameter("userId"));
            int vacId = Integer.parseInt(req.getParameter("vacId"));
            String userName = req.getParameter("userName");
            String userSurname = req.getParameter("userSurname");
            int userAge = Integer.parseInt(req.getParameter("userAge"));
            String userLanguage = req.getParameter("userLanguage");
            String userWorkExperience = req.getParameter("userWorkExperience");
            String userInfo = req.getParameter("userInfo");

            resumeService.create(new Resume(userId, vacId, userName, userSurname, userAge, userLanguage, userWorkExperience, userInfo));
            vacRespondedService.create(new VacResponded(userId, vacId));
            Command command = CommandProvider.getInstance().getCommand(CommandName.HOME_PAGE.name());
            command.execute(req, resp);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}
