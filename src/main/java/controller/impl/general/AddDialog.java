package controller.impl.general;

import bean.Message;
import bean.User;
import controller.Command;
import controller.CommandName;
import controller.CommandProvider;
import exception.CommandException;
import exception.ServiceException;
import org.apache.log4j.Logger;
import service.MessageService;
import service.ServiceFactory;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddDialog implements Command {
    private static Logger log = Logger.getLogger(AddDialog.class);
    private UserService userService = ServiceFactory.getInstance().getUserService();
    private MessageService messageService = ServiceFactory.getInstance().getMessageService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        try {

            User ownUser = (User) (User) req.getSession().getAttribute("user");

            String name = req.getParameter("name");
            User otherUser = userService.getUserByName(name);

            if (otherUser != null && !otherUser.equals(ownUser)) {
                Message message = new Message(ownUser.getId(), otherUser.getId(), "Added you in dialog");
                messageService.create(message);
            }

            Command command = CommandProvider.getInstance().getCommand(CommandName.ALL_DIALOGS_PAGE.name());
            command.execute(req, resp);

        } catch (ServiceException e) {
            log.error(e);
            throw new CommandException(e);
        }
    }
}
