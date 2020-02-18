package controller.impl.general;

import bean.Message;
import controller.Command;
import controller.impl.general.AddDialog;
import exception.CommandException;
import exception.ServiceException;
import org.apache.log4j.Logger;
import service.MessageService;
import service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Dialog implements Command {
    private static Logger log = Logger.getLogger(AddDialog.class);
    private MessageService messageService = ServiceFactory.getInstance().getMessageService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {

        try {

            int ownUserId = Integer.parseInt(req.getParameter("ownUserId"));
            int otherUserId = Integer.parseInt(req.getParameter("otherUserId"));
            String content = req.getParameter("message");

            Message message = new Message(ownUserId, otherUserId, content);
            messageService.create(message);
            resp.sendRedirect("/welcome?otherUserId=" + otherUserId + "&command=dialog_page");


        } catch (ServiceException | IOException e) {
            log.error(e);
            throw new CommandException(e);
        }
    }


}
