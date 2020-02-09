package controller.impl;

import bean.User;
import controller.Command;
import controller.PageAccess;
import exception.CommandException;
import exception.ServiceException;
import service.InterviewService;
import service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProfilePage extends PageAccess implements Command {
    private InterviewService interviewService = ServiceFactory.getInstance().getInterviewService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        try {
            User user = isLogin(req);
            if (user != null) {
                req.setAttribute("interview", interviewService.getAllInterviewBySeeker(user));
                req.getRequestDispatcher("/profile.jsp").forward(req, resp);
            } else {
                resp.sendRedirect("/login.jsp");
            }
        } catch (IOException | ServletException | ServiceException e) {
            throw new CommandException(e);
        }
    }

    @Override
    public User isLogin(HttpServletRequest request) {
        return checkLogin(request);
    }
}
