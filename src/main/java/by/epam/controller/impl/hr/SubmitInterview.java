package by.epam.controller.impl.hr;

import by.epam.bean.Interview;
import by.epam.controller.Command;
import by.epam.controller.CommandName;
import by.epam.controller.CommandProvider;
import by.epam.exception.CommandException;
import by.epam.exception.ServiceException;
import org.apache.log4j.Logger;
import by.epam.service.InterviewService;
import by.epam.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;

public class SubmitInterview implements Command {
    private static Logger log = Logger.getLogger(SubmitInterview.class);
    private InterviewService interviewService = ServiceFactory.getInstance().getInterviewService();

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
            req.setAttribute("success", "table-success");
            Command command = CommandProvider.getInstance().getCommand(CommandName.PROFILE_PAGE.name());
            command.execute(req, resp);
        } catch (ServiceException e) {
            log.error(e);
            throw new CommandException(e);
        }
    }
}
