package controller.impl;

import bean.Message;
import bean.User;
import bean.dto.Dialog;
import controller.Command;
import controller.PageAccess;
import exception.CommandException;
import exception.ServiceException;
import service.MessageService;
import service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AllDialogsPage extends PageAccess implements Command {
    private MessageService messageService = ServiceFactory.getInstance().getMessageService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        try {
            User user = isLogin(req);
            if (user != null) {
                List<Message> messages = messageService.getAllMessageByUser(user);
                List<Dialog> allDialogs = messageService.createDialogs(messages, user.getId());

                req.setAttribute("dialogs", allDialogs);
                req.getRequestDispatcher("/allDialogs.jsp").forward(req, resp);

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
