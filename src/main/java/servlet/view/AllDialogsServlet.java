package servlet.view;

import bean.Message;
import bean.User;
import bean.dto.Dialog;
import dao.MessageDao;
import dao.impl.JdbcMessageDao;
import service.MessageService;
import service.impl.MessageServiceImpl;
import servlet.PageAccess;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/allDialogs")
public class AllDialogsServlet extends MainServlet implements PageAccess {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = isLogin(req);
        if (user != null) {
            MessageDao messageDao = new JdbcMessageDao();
            MessageService service = new MessageServiceImpl();

            List<Message> messages = messageDao.getAllMessageByUser(user);
            List<Dialog> allDialogs = service.createDialogs(messages,user.getId());

            req.setAttribute("dialogs" , allDialogs);
            req.getRequestDispatcher("/allDialogs.jsp").forward(req,resp);

        } else {
            resp.sendRedirect("/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    public User isLogin(HttpServletRequest request) {
        return checkLogin(request);
    }
}
