package controller.impl;

import bean.User;
import controller.Command;
import controller.CommandName;
import controller.CommandProvider;
import controller.PageAccess;
import exception.CommandException;
import exception.ServiceException;
import service.MessageService;
import service.ServiceFactory;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteDialog extends PageAccess implements Command {
    private MessageService messageService = ServiceFactory.getInstance().getMessageService();
    private UserService userService = ServiceFactory.getInstance().getUserService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        try {
            User ownUser = isLogin(req);
            if (ownUser != null) {

                String name = req.getParameter("name");
                User otherUser = userService.getUserByName(name);
                messageService.deleteAllMessageByUsers(ownUser, otherUser);
                Command command = CommandProvider.getInstance().getCommand(CommandName.ALL_DIALOGS_PAGE.name());
                command.execute(req, resp);
            } else {
                resp.sendRedirect("/login.jsp");
            }
        } catch (ServiceException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User isLogin(HttpServletRequest request) {
        return checkLogin(request);
    }
}
