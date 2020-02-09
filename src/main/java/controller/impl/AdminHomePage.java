package controller.impl;

import bean.Role;
import bean.User;
import controller.Command;
import controller.PageAccess;
import exception.CommandException;
import exception.ServiceException;
import service.ServiceFactory;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminHomePage extends PageAccess implements Command {
    private UserService userService = ServiceFactory.getInstance().getUserService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        try {
            User user = isLogin(req);
            if (user != null && user.getRole() == Role.ADMIN) {
                RequestDispatcher dispatcher = req.getRequestDispatcher("/adminHome.jsp");
                req.setAttribute("seekers", userService.getAllSeekers());
                req.setAttribute("hrs", userService.getAllHR());
                dispatcher.forward(req, resp);
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
