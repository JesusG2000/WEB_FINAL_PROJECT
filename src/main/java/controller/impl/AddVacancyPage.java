package controller.impl;

import bean.Role;
import bean.User;
import controller.Command;
import controller.PageAccess;
import exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddVacancyPage extends PageAccess implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        try {
            //User user = isLogin(req);
           // if (user != null && user.getRole() == Role.HR) {
                resp.sendRedirect("/addVacancy.jsp");
          //  } else {
            //    resp.sendRedirect("/login.jsp");
           // }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User isLogin(HttpServletRequest request) {
        return checkLogin(request);
    }
}
