package controller.impl;

import bean.Message;
import bean.User;
import controller.Command;
import controller.PageAccess;
import exception.CommandException;
import exception.ServiceException;
import service.MessageService;
import service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Dialog extends PageAccess implements Command {
    private MessageService messageService = ServiceFactory.getInstance().getMessageService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {

        try {
            User ownUser = isLogin(req);
            if (ownUser != null) {
                int ownUserId = Integer.parseInt(req.getParameter("ownUserId"));
                int otherUserId = Integer.parseInt(req.getParameter("otherUserId"));
                String content = req.getParameter("message");

                Message message = new Message(ownUserId, otherUserId, content);
                messageService.create(message);
                resp.sendRedirect("/welcome?otherUserId=" + otherUserId + "&command=dialog_page");

            }
        } catch (ServiceException | IOException e) {
            throw new CommandException(e);
        }
    }

    @Override
    public User isLogin(HttpServletRequest request) {
        return checkLogin(request);
    }
}
