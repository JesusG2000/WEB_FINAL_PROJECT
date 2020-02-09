package controller;

import exception.CommandException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/welcome")
public class Controller extends HttpServlet {
    private final CommandProvider commandProvider = new CommandProvider();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        complete(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        complete(req, resp);
    }

    private void complete(HttpServletRequest req, HttpServletResponse resp) {
        String commandName = req.getParameter("command");

        if (commandName == null) {
            commandName = CommandName.MAIN_PAGE.name();
        }
        System.out.println(commandName);
        Command command = commandProvider.getCommand(commandName);

        try {
            command.execute(req,resp);
        } catch (CommandException e) {
            System.out.println(e.getMessage());
        }

    }
}
