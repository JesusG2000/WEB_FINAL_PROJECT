package by.epam.controller.impl.general;

import by.epam.bean.User;
import by.epam.controller.Command;
import by.epam.controller.CommandName;
import by.epam.controller.CommandProvider;
import by.epam.exception.CommandException;
import by.epam.exception.ServiceException;
import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;
import by.epam.service.ServiceFactory;
import by.epam.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ChangePassword implements Command {
    private static Logger log = Logger.getLogger(ChangePassword.class);
    private UserService userService = ServiceFactory.getInstance().getUserService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        try {
            Command command = CommandProvider.getInstance().getCommand(CommandName.PROFILE_PAGE.name());
            HttpSession session = req.getSession();
            User user = (User) session.getAttribute("user");
            String oldPassword = req.getParameter("oldPassword");
            String newPassword = req.getParameter("newPassword");
            String confirmPassword = req.getParameter("confirmPassword");
            String message = null;

            if (BCrypt.checkpw(oldPassword, user.getPassword())) {
                if ((newPassword.length() >= 4 && newPassword.length() <= 16)
                        && (confirmPassword.length() >= 4 && confirmPassword.length() <= 16)) {
                    if (newPassword.equals(confirmPassword)) {
                        user.setPassword(BCrypt.hashpw(newPassword, BCrypt.gensalt()));
                        userService.update(user);
                        message = "password was changed";
                        session.setAttribute("user", user);
                    } else {
                        message = "passwords dont match";
                    }
                } else {
                    message = "length must be from 4 to 16";
                }
            } else {
                message = "not correct old password";
            }
            req.setAttribute("message", message);
            command.execute(req, resp);
        } catch (ServiceException e) {
            log.error(e);
            throw new CommandException(e);
        }
    }
}
