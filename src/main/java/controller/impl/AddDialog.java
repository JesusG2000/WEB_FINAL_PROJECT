package controller.impl;

import bean.Message;
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

public class AddDialog extends PageAccess implements Command {
    private UserService userService = ServiceFactory.getInstance().getUserService();
    private MessageService messageService = ServiceFactory.getInstance().getMessageService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        try {
            User ownUser = isLogin(req);
            //if (ownUser != null) {
                String name = req.getParameter("name");
                User otherUser = userService.getUserByName(name);

                if (otherUser != null && !otherUser.equals(ownUser)) {
                    Message message = new Message(ownUser.getId(), otherUser.getId(), "Added you in dialog");
                    messageService.create(message);
                }

                Command command = CommandProvider.getInstance().getCommand(CommandName.ALL_DIALOGS_PAGE.name());
                command.execute(req, resp);
           // } else {
           //     resp.sendRedirect("/login.jsp");
            //}
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }

    @Override
    public User isLogin(HttpServletRequest request) {
        return checkLogin(request);
    }
}
