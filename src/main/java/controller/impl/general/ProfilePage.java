package controller.impl.general;

import bean.Interview;
import bean.User;
import controller.Command;
import exception.CommandException;
import exception.ServiceException;
import org.apache.log4j.Logger;
import service.InterviewService;
import service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class ProfilePage implements Command {
    private static Logger log = Logger.getLogger(ProfilePage.class);
    private InterviewService interviewService = ServiceFactory.getInstance().getInterviewService();
    private int count = 3;
    private int start;
    private int finish;

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        try {
            HttpSession session = req.getSession();

            User user = (User) session.getAttribute("user");

            setPageValue(req, session);

            List<Interview> interviews = interviewService.getAllInterviewBySeeker(user);
            interviews.sort(new SortId());

            enoughSize(interviews);

            session.setAttribute("start", start);
            session.setAttribute("finish", finish);

            interviews = pagination(interviews);

            req.setAttribute("interview", interviews);
            req.setAttribute("count", count);
            req.getRequestDispatcher("/jsp/profile.jsp").forward(req, resp);

        } catch (IOException | ServletException | ServiceException e) {
            log.error(e);
            throw new CommandException(e);
        }
    }

    private void setPageValue(HttpServletRequest req, HttpSession session) {

        int startInSession = (int) session.getAttribute("start");
        int finishInSession = (int) session.getAttribute("finish");

        if (req.getParameter("start") != null && req.getParameter("finish") != null) {
            start = Integer.parseInt(req.getParameter("start"));
            finish = Integer.parseInt(req.getParameter("finish"));

        } else {
            start = startInSession;
            finish = finishInSession;
        }
    }

    private void enoughSize(List<Interview> interviews) {
        if (start > interviews.size()) {
            start -= count;
            finish -= count;
        }
        if (start < 0) {
            start += count;
            finish += count;
        }
    }

    private List<Interview> pagination(List<Interview> interviews) {
        List<Interview> pagInterview = new LinkedList<>();

        if (finish > interviews.size()) {
            finish = interviews.size();
        }
        for (int i = start; i < finish; i++) {
            pagInterview.add(interviews.get(i));
        }
        return pagInterview;
    }

    private static class SortId implements Comparator<Interview> {

        @Override
        public int compare(Interview o1, Interview o2) {
            return o2.getId() - o1.getId();
        }
    }
}
