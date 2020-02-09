package controller.impl;

import bean.Message;
import bean.Role;
import bean.User;
import bean.Vacancy;
import controller.Command;
import controller.CommandName;
import controller.CommandProvider;
import controller.PageAccess;
import exception.CommandException;
import exception.ServiceException;
import service.MessageService;
import service.ServiceFactory;
import service.UserService;
import service.VacancyService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class DeleteVacancy extends PageAccess implements Command {
   private VacancyService vacancyService = ServiceFactory.getInstance().getVacancyService();
   private UserService userService = ServiceFactory.getInstance().getUserService();
   private MessageService messageService = ServiceFactory.getInstance().getMessageService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        try {
            User user = isLogin(req);
           // if (user != null && user.getRole() == Role.HR) {
                int vacancyId = Integer.parseInt(req.getParameter("id"));

                notifySeekers(user ,vacancyId);
                vacancyService.deleteById(vacancyId);

                Command command = CommandProvider.getInstance().getCommand(CommandName.HOME_PAGE.name());
                command.execute(req,resp);
          //  } else {
           //    resp.sendRedirect("/login.jsp");
            //}
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    private void notifySeekers(User user, int vacancyId) throws ServiceException {
        Vacancy vacancy = vacancyService.readById(vacancyId);
        List<User> userList = userService.getAllSeekers();
        for (User value : userList) {
            int senderId = user.getId();
            int getterId = value.getId();
            String content = "Vacancy with name = " + vacancy.getName() + " was deleted";
            Message message = new Message(senderId, getterId, content);
            messageService.create(message);
        }
    }

    @Override
    public User isLogin(HttpServletRequest request) {
        return checkLogin(request);
    }
}
