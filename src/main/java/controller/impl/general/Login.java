package controller.impl.general;

import bean.Role;
import bean.User;
import controller.Command;
import controller.CommandName;
import controller.CommandProvider;
import exception.CommandException;
import exception.ServiceException;
import org.apache.log4j.Logger;
import service.ServiceFactory;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Login implements Command {
    private static Logger log = Logger.getLogger(Login.class);
    private UserService userService = ServiceFactory.getInstance().getUserService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        HttpSession session = req.getSession();

        try {
            String username = req.getParameter("name");
            String password = req.getParameter("password");
            User user = new User(username, password);
            if (userService.isExist(user)) {

                user = userService.getUserByName(username);
                session.setAttribute("user", user);


                setCookie(req,resp ,session);
                setAttributes(user.getRole() ,session);

                Command command = CommandProvider.getInstance().getCommand(CommandName.MAIN_PAGE.name());
                command.execute(req, resp);

            } else {
                RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/login.jsp");
                req.setAttribute("message", "Not correct login or password");
                dispatcher.forward(req, resp);
            }
        } catch (IOException | ServletException | ServiceException e) {
            log.error(e);
            throw new CommandException("Error in redirect to page", e);
        }
    }

    private void setCookie(HttpServletRequest req, HttpServletResponse resp, HttpSession session) {
        Cookie[] cookies = req.getCookies();
        boolean isExist = false;
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("lang")){
//                System.out.println("in if" + cookie.getValue());
                session.setAttribute("locale",cookie.getValue());
                isExist=true;
            }
        }
        if(!isExist){

            Cookie cookie = new Cookie("lang","en");
            cookie.setMaxAge(24*60*60);
            session.setAttribute("locale",cookie.getValue());
//            System.out.println("not exist " + cookie.getValue());
            resp.addCookie(cookie);
        }
    }

    private void setAttributes(Role role, HttpSession session) {
        switch (role){
            case SEEKER:{
                session.setAttribute("start", 0);
                session.setAttribute("finish", 3);
                session.setAttribute("count", 3);
                break;
            }
            case HR:
            case ADMIN: {
                session.setAttribute("start", 0);
                session.setAttribute("finish", 6);
                session.setAttribute("count", 6);
                break;
            }
        }
    }
}
