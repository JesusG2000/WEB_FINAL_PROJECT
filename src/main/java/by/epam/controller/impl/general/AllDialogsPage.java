package by.epam.controller.impl.general;

import by.epam.bean.Message;
import by.epam.bean.User;
import by.epam.bean.dto.Dialog;
import by.epam.controller.Command;
import by.epam.exception.CommandException;
import by.epam.exception.ServiceException;
import org.apache.log4j.Logger;
import by.epam.service.MessageService;
import by.epam.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AllDialogsPage implements Command {
    private static Logger log = Logger.getLogger(AllDialogsPage.class);
    private MessageService messageService = ServiceFactory.getInstance().getMessageService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        try {

            User user = (User) req.getSession().getAttribute("user");

            List<Message> messages = messageService.getAllMessageByUser(user);
            List<Dialog> allDialogs = messageService.createDialogs(messages, user.getId());

            req.setAttribute("dialogs", allDialogs);
            req.getRequestDispatcher("/jsp/allDialogs.jsp").forward(req, resp);


        } catch (IOException | ServiceException | ServletException e) {
            log.error(e);
            throw new CommandException(e);
        }
    }


}
