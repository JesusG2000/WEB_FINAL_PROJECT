package controller.impl;

import bean.Role;
import bean.User;
import bean.Vacancy;
import controller.Command;
import controller.PageAccess;
import exception.CommandException;
import exception.ServiceException;
import service.ServiceFactory;
import service.UserService;
import service.VacancyService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateVacancyPage extends PageAccess implements Command {
    private VacancyService vacancyService = ServiceFactory.getInstance().getVacancyService();
    private UserService userService = ServiceFactory.getInstance().getUserService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        try {
            User user = isLogin(req);
            if (user != null && user.getRole() == Role.HR) {

                Vacancy vacancy = vacancyService.readById(Integer.parseInt(req.getParameter("id")));
                req.setAttribute("vacancy", vacancy);
                req.setAttribute("seekers", userService.getAllByVacancy(vacancy));
                RequestDispatcher dispatcher = req.getRequestDispatcher("/vacancyInfo.jsp");
                dispatcher.forward(req, resp);
            } else {
                resp.sendRedirect("/login.jsp");

            }
        } catch (IOException | ServiceException | ServletException e) {
            throw new CommandException(e);
        }
    }

    @Override
    public User isLogin(HttpServletRequest request) {
        return checkLogin(request);
    }
}
