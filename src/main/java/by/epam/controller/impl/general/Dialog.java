package by.epam.controller.impl.general;

import by.epam.bean.Message;
import by.epam.controller.Command;
import by.epam.exception.CommandException;
import by.epam.exception.ServiceException;
import org.apache.log4j.Logger;
import by.epam.service.MessageService;
import by.epam.service.ServiceFactory;

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
