package by.epam.controller.impl.general;

import by.epam.bean.Message;
import by.epam.bean.User;
import by.epam.bean.dto.Dialog;
import by.epam.controller.Command;
import by.epam.exception.CommandException;
import by.epam.exception.ServiceException;
import org.apache.log4j.Logger;
import by.epam.service.DialogService;
import by.epam.service.MessageService;
import by.epam.service.ServiceFactory;
import by.epam.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

public class DialogPage implements Command {
    private static Logger log = Logger.getLogger(DialogPage.class);
    private MessageService messageService = ServiceFactory.getInstance().getMessageService();
    private DialogService dialogService = ServiceFactory.getInstance().getDialogService();
    private UserService userService = ServiceFactory.getInstance().getUserService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        try {
            User ownUser = (User) req.getSession().getAttribute("user");


            int otherUserId = Integer.parseInt(req.getParameter("otherUserId"));
            User otherUser = userService.readById(otherUserId);

            List<Message> messages = messageService.getAllMessageByUser(ownUser);
            List<Dialog> dialogs = messageService.createDialogs(messages, ownUser.getId());
            Dialog finalDialog = dialogService.getDialogByUsers(dialogs, ownUser, otherUser);

            List<Message> sort = finalDialog.getMessages();
            sort.sort(new SortId());

            finalDialog.setMessages(sort);
            req.setAttribute("finalDialog", finalDialog);

            req.getRequestDispatcher("/jsp/dialog.jsp").forward(req, resp);


        } catch (ServletException | IOException | ServiceException e) {
            throw new CommandException(e);
        }
    }


    private static class SortId implements Comparator<Message> {

        @Override
        public int compare(Message o1, Message o2) {
            return -o1.getId() + o2.getId();
        }
    }
}
