package controller.impl.general;

import bean.Interview;
import bean.Role;
import bean.User;
import bean.Vacancy;
import controller.Command;
import exception.CommandException;
import exception.ServiceException;
import org.apache.log4j.Logger;
import service.InterviewService;
import service.ServiceFactory;
import service.UserService;
import service.VacancyService;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

public class ProfilePage implements Command {
    private static Logger log = Logger.getLogger(ProfilePage.class);
    private InterviewService interviewService = ServiceFactory.getInstance().getInterviewService();
    private UserService userService = ServiceFactory.getInstance().getUserService();
    private VacancyService vacancyService = ServiceFactory.getInstance().getVacancyService();
    private int count;
    private int start;
    private int finish;

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        try {
            HttpSession session = req.getSession();

            User user = (User) session.getAttribute("user");

            setPageValue(req, session);

            switch (user.getRole()){
                case SEEKER:{
                    seekerParams(req, session, user);
                    break;
                }
                case ADMIN:{
                    adminParams(req, session);
                    break;
                }
                case HR:{
                    hrParams(req, session);
                    break;
                }
            }
            req.setAttribute("count", count);
            req.getRequestDispatcher("/jsp/profile.jsp").forward(req, resp);

        } catch (IOException | ServletException | ServiceException e) {
            log.error(e);
            throw new CommandException(e);
        }
    }

    private void hrParams(HttpServletRequest req, HttpSession session) throws ServiceException {
        List<Vacancy> vacancies = vacancyService.getAllVacancy();


        enoughSize(vacancies);

        session.setAttribute("start", start);
        session.setAttribute("finish", finish);

        vacancies=pagination(vacancies);


        Map<Vacancy,List<User>> vacancyListUser = new HashMap<>();
        for(Vacancy v : vacancies){
            vacancyListUser.put(v,userService.getAllByVacancy(v));
        }

        req.setAttribute("vacancyListUser",vacancyListUser);
        req.setAttribute("vacancies",vacancies);
        req.setAttribute("interviewService",interviewService);
    }

    private void adminParams(HttpServletRequest req, HttpSession session) throws ServiceException {
        List<User> seekers = userService.getAllSeekers();
        List<User> hrs = userService.getAllHR();
        List<User> allUsers = new LinkedList<>();
        allUsers.addAll(seekers);
        allUsers.addAll(hrs);

        enoughSize(allUsers);

        session.setAttribute("start", start);
        session.setAttribute("finish", finish);

        allUsers=pagination(allUsers);

        req.setAttribute("allUsers", allUsers);
    }

    private void seekerParams(HttpServletRequest req, HttpSession session, User user) throws ServiceException {
        List<Interview> interviews = interviewService.getAllInterviewBySeeker(user);
        interviews.sort(new SortId());

        enoughSize(interviews);

        session.setAttribute("start", start);
        session.setAttribute("finish", finish);

        interviews = pagination(interviews);

        req.setAttribute("interview", interviews);
        req.setAttribute("vacancyService", vacancyService);
        req.setAttribute("userService", userService);
    }

    private void setPageValue(HttpServletRequest req, HttpSession session) {

        int startInSession = (int) session.getAttribute("start");
        int finishInSession = (int) session.getAttribute("finish");
        count = (int) session.getAttribute("count");

        if (req.getParameter("start") != null && req.getParameter("finish") != null) {
            start = Integer.parseInt(req.getParameter("start"));
            finish = Integer.parseInt(req.getParameter("finish"));

        } else {
            start = startInSession;
            finish = finishInSession;
        }
    }

    private <T> void enoughSize(List<T> objects) {
        if (start > objects.size()) {
            start -= count;
            finish -= count;
        }
        if (start < 0) {
            start += count;
            finish += count;
        }
    }

    private <T> List<T> pagination(List<T> objects) {
        List<T> pagObject = new LinkedList<>();

        if (finish > objects.size()) {
            finish = objects.size();
        }
        for (int i = start; i < finish; i++) {
            pagObject.add(objects.get(i));
        }
        return pagObject;
    }

    private static class SortId implements Comparator<Interview> {

        @Override
        public int compare(Interview o1, Interview o2) {
            return o2.getId() - o1.getId();
        }
    }
}
