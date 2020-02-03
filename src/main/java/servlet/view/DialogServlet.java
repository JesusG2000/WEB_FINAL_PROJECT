package servlet.view;

import bean.Message;
import bean.User;
import bean.dto.Dialog;
import dao.Dao;
import dao.MessageDao;
import dao.impl.JdbcMessageDao;
import dao.impl.JdbcUserDao;
import service.DialogService;
import service.MessageService;
import service.impl.DialogServiceImpl;
import service.impl.MessageServiceImpl;
import servlet.PageAccess;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/dialog")
public class DialogServlet extends MainServlet implements PageAccess {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User ownUser = isLogin(req);
        if (ownUser != null) {
            Dao<User> dao = new JdbcUserDao();
            MessageDao messageDao = new JdbcMessageDao();
            MessageService service = new MessageServiceImpl();
            DialogService dialogService = new DialogServiceImpl();

            int otherUserId = Integer.parseInt(req.getParameter("otherUserId"));
            User otherUser = dao.read(new User(otherUserId));

            List<Message> messages = messageDao.getAllMessageByUser(ownUser);
            List<Dialog> dialogs = service.createDialogs(messages, ownUser.getId());
            Dialog finalDialog = dialogService.getDialogByUsers(dialogs, ownUser, otherUser);
            req.setAttribute("finalDialog", finalDialog);

            req.getRequestDispatcher("/dialog.jsp").forward(req, resp);

        } else {
            resp.sendRedirect("/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Dao<Message> messageDao = new JdbcMessageDao();

        int ownUserId = Integer.parseInt(req.getParameter("ownUserId"));
        int otherUserId = Integer.parseInt(req.getParameter("otherUserId"));
        String content = req.getParameter("message");

        Message message = new Message(ownUserId, otherUserId, content);
        messageDao.create(message);
        resp.sendRedirect("/dialog?otherUserId="+otherUserId);

    }

    @Override
    public User isLogin(HttpServletRequest request) {
        return checkLogin(request);
    }
}
