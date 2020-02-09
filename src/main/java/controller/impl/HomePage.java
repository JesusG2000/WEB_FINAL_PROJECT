package controller.impl;

import bean.User;
import controller.Command;
import controller.PageAccess;
import exception.CommandException;
import exception.ServiceException;
import service.ServiceFactory;
import service.VacancyService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.CompletionException;

public class HomePage extends PageAccess implements Command {
    private VacancyService vacancyService = ServiceFactory.getInstance().getVacancyService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        try {
            User user = isLogin(req);
            if (user != null) {
                RequestDispatcher dispatcher = req.getRequestDispatcher("/home.jsp");
                req.setAttribute("currentUser", user);
                req.setAttribute("vacancies", vacancyService.getAllVacancy());
                dispatcher.forward(req, resp);
            } else {
                resp.sendRedirect("/login.jsp");
            }
        } catch (IOException | ServletException | ServiceException e) {
            throw new CompletionException(e);
        }
    }

    @Override
    public User isLogin(HttpServletRequest request) {
        return checkLogin(request);
    }
}
