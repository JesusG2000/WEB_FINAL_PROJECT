package controller.impl;

import bean.User;
import controller.Command;
import exception.CommandException;
import exception.ServiceException;
import org.apache.log4j.Logger;
import service.InterviewService;
import service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProfilePage implements Command {
    private static Logger log = Logger.getLogger(ProfilePage.class);
    private InterviewService interviewService = ServiceFactory.getInstance().getInterviewService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        try {
            User user = (User) req.getAttribute("user");

            req.setAttribute("interview", interviewService.getAllInterviewBySeeker(user));
            req.getRequestDispatcher("/profile.jsp").forward(req, resp);

        } catch (IOException | ServletException | ServiceException e) {
            log.error(e);
            throw new CommandException(e);
        }
    }


}
