package controller.impl;

import bean.Interview;
import bean.User;
import controller.Command;
import controller.CommandName;
import controller.CommandProvider;
import exception.CommandException;
import exception.ServiceException;
import org.apache.log4j.Logger;
import service.InterviewService;
import service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;

public class SubmitInterview implements Command {
    private InterviewService interviewService = ServiceFactory.getInstance().getInterviewService();
    private static Logger log = Logger.getLogger(SubmitInterview.class);
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        try {

            int hrId = Integer.parseInt(req.getParameter("hrId"));
            int seekerId = Integer.parseInt(req.getParameter("seekerId"));
            Date date = Date.valueOf(req.getParameter("date"));
            String comment = req.getParameter("comment");
            int vacId = Integer.parseInt(req.getParameter("vacancyId"));

            Interview interview = new Interview(hrId, seekerId, vacId, date, comment);

            interviewService.create(interview);

            Command command = CommandProvider.getInstance().getCommand(CommandName.HOME_PAGE.name());
            command.execute(req, resp);
        } catch (ServiceException e) {
            log.error(e);
            throw new CommandException(e);
        }
    }
}
