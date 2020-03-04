package by.epam.filter;

import by.epam.bean.Role;
import by.epam.bean.User;
import by.epam.controller.CommandName;
import by.epam.exception.ServiceException;
import by.epam.service.ServiceFactory;
import by.epam.service.UserService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class CommandFilter implements Filter {
    private Set<String> seekerCommands = new HashSet<>();
    private Set<String> hrCommands = new HashSet<>();
    private Set<String> adminCommands = new HashSet<>();
    private Set<String> defaultCommands = new HashSet<>();
    private UserService userService = ServiceFactory.getInstance().getUserService();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        seekerCommands.add(CommandName.SUBSCRIBE_TO_VACANCY.name());
        seekerCommands.add(CommandName.ADD_DIALOG.name());
        seekerCommands.add(CommandName.ALL_DIALOGS_PAGE.name());
        seekerCommands.add(CommandName.DELETE_DIALOG.name());
        seekerCommands.add(CommandName.DIALOG.name());
        seekerCommands.add(CommandName.DIALOG_PAGE.name());
        seekerCommands.add(CommandName.HOME_PAGE.name());
        seekerCommands.add(CommandName.PROFILE_PAGE.name());
        seekerCommands.add(CommandName.LOGIN.name());
        seekerCommands.add(CommandName.LOGOUT.name());
        seekerCommands.add(CommandName.CHANGE_LANGUAGE.name());
        seekerCommands.add(CommandName.CHANGE_PASSWORD.name());
        seekerCommands.add(CommandName.SUBMIT_RESUME.name());
        seekerCommands.add(CommandName.RESUME_PAGE.name());

        hrCommands.add(CommandName.ADD_VACANCY_PAGE.name());
        hrCommands.add(CommandName.ADD_VACANCY.name());
        hrCommands.add(CommandName.DELETE_VACANCY.name());
        hrCommands.add(CommandName.INTERVIEW_PAGE.name());
        hrCommands.add(CommandName.SUBMIT_INTERVIEW.name());
        hrCommands.add(CommandName.UPDATE_VACANCY.name());
        hrCommands.add(CommandName.UPDATE_VACANCY_PAGE.name());
        hrCommands.add(CommandName.ADD_DIALOG.name());
        hrCommands.add(CommandName.ALL_DIALOGS_PAGE.name());
        hrCommands.add(CommandName.DELETE_DIALOG.name());
        hrCommands.add(CommandName.DIALOG.name());
        hrCommands.add(CommandName.DIALOG_PAGE.name());
        hrCommands.add(CommandName.HOME_PAGE.name());
        hrCommands.add(CommandName.PROFILE_PAGE.name());
        hrCommands.add(CommandName.LOGOUT.name());
        hrCommands.add(CommandName.LOGIN.name());
        hrCommands.add(CommandName.CHANGE_LANGUAGE.name());
        hrCommands.add(CommandName.CHANGE_PASSWORD.name());

        adminCommands.add(CommandName.ADMIN_HOME_PAGE.name());
        adminCommands.add(CommandName.DELETE_USER.name());
        adminCommands.add(CommandName.ADD_DIALOG.name());
        adminCommands.add(CommandName.ALL_DIALOGS_PAGE.name());
        adminCommands.add(CommandName.DELETE_DIALOG.name());
        adminCommands.add(CommandName.DIALOG.name());
        adminCommands.add(CommandName.DIALOG_PAGE.name());
        adminCommands.add(CommandName.HOME_PAGE.name());
        adminCommands.add(CommandName.PROFILE_PAGE.name());
        adminCommands.add(CommandName.LOGIN.name());
        adminCommands.add(CommandName.LOGOUT.name());
        adminCommands.add(CommandName.CHANGE_LANGUAGE.name());
        adminCommands.add(CommandName.CHANGE_PASSWORD.name());

        defaultCommands.add(CommandName.REGISTRATION_PAGE.name());
        // defaultCommands.add(CommandName.LOGIN_PAGE.name());
        defaultCommands.add(CommandName.LOGOUT.name());
        defaultCommands.add(CommandName.MAIN_PAGE.name());
        defaultCommands.add(CommandName.REGISTRATION.name());
        defaultCommands.add(CommandName.LOGIN.name());


    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();

        User user = (User) session.getAttribute("user");
        String commandName = req.getParameter("command");


        if ((commandName == null)) {
            filterChain.doFilter(req, resp);
            return;
        } else {
            commandName = commandName.toUpperCase();
        }

        if (user != null) {
            try {
                user = userService.readById(user.getId());
            } catch (ServiceException e) {
                e.printStackTrace();
            }
            commandName = commandName.toUpperCase();
            if (user != null) {

                if (user.getRole() == Role.SEEKER && seekerCommands.contains(commandName)) {
                   // System.out.println(Role.SEEKER);
                    filterChain.doFilter(req, resp);

                } else if (user.getRole() == Role.HR && hrCommands.contains(commandName)) {
                   // System.out.println(Role.HR);
                    filterChain.doFilter(req, resp);

                } else if (user.getRole() == Role.ADMIN && adminCommands.contains(commandName)) {
                   // System.out.println(Role.ADMIN);
                    filterChain.doFilter(req, resp);

                } else {
                    resp.sendRedirect("/welcome");
                }

            } else {
                session.removeAttribute("user");
                resp.sendRedirect("/welcome");
            }

        } else if (defaultCommands.contains(commandName)) {
            filterChain.doFilter(req, resp);

        } else {
            resp.sendRedirect("/welcome");
        }
    }


    @Override
    public void destroy() {

    }
}
