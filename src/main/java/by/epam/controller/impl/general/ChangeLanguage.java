package by.epam.controller.impl.general;

import by.epam.controller.Command;
import by.epam.controller.CommandName;
import by.epam.controller.CommandProvider;
import by.epam.exception.CommandException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ChangeLanguage implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        HttpSession session = req.getSession();
        String language = req.getParameter("language");
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("lang")) {
                cookie.setValue(language);
                resp.addCookie(cookie);
                session.setAttribute("locale", cookie.getValue());

            }
        }

        Command command = CommandProvider.getInstance().getCommand(CommandName.MAIN_PAGE.name());
        command.execute(req, resp);
    }
}
