package controller.impl;

import bean.Message;
import bean.User;
import bean.dto.Dialog;
import controller.Command;
import controller.PageAccess;
import exception.CommandException;
import exception.ServiceException;
import service.DialogService;
import service.MessageService;
import service.ServiceFactory;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class DialogPage extends PageAccess implements Command {
    private MessageService messageService = ServiceFactory.getInstance().getMessageService();
    private DialogService dialogService = ServiceFactory.getInstance().getDialogService();
    private UserService userService = ServiceFactory.getInstance().getUserService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        try {
            User ownUser = isLogin(req);
           // if (ownUser != null) {

                int otherUserId = Integer.parseInt(req.getParameter("otherUserId"));
                User otherUser = userService.readById(otherUserId);

                List<Message> messages = messageService.getAllMessageByUser(ownUser);
                List<Dialog> dialogs = messageService.createDialogs(messages, ownUser.getId());
                Dialog finalDialog = dialogService.getDialogByUsers(dialogs, ownUser, otherUser);
                req.setAttribute("finalDialog", finalDialog);

                req.getRequestDispatcher("/dialog.jsp").forward(req, resp);

            //} else {
            //    resp.sendRedirect("/login.jsp");
           // }
        } catch (ServletException | IOException | ServiceException e) {
            throw new CommandException(e);
        }
    }

    @Override
    public User isLogin(HttpServletRequest request) {
        return checkLogin(request);
    }
}
