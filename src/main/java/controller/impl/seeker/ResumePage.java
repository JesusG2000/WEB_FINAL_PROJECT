package controller.impl.seeker;

import controller.Command;
import controller.CommandName;
import controller.CommandProvider;
import exception.CommandException;
import exception.ServiceException;
import service.ResumeService;
import service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResumePage implements Command {
    private ResumeService resumeService = ServiceFactory.getInstance().getResumeService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        try {
            int vacId = Integer.parseInt(req.getParameter("vacId"));
            int userId = Integer.parseInt(req.getParameter("userId"));
            if (resumeService.readByUserIdVacancyId(userId, vacId) != null) {
                String message = "you've already subscribed";
                req.setAttribute("message", message);
                Command command = CommandProvider.getInstance().getCommand(CommandName.HOME_PAGE.name());
                command.execute(req, resp);
            } else {
                req.setAttribute("vacId", vacId);
                req.setAttribute("userId", userId);
                req.getRequestDispatcher("/jsp/resume.jsp").forward(req, resp);
            }

        } catch (ServletException | ServiceException | IOException e) {
            throw new CommandException(e);
        }
    }
}
