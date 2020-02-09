package controller;

import bean.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public abstract class PageAccess {
   public abstract User isLogin(HttpServletRequest request);
   public User checkLogin(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (User) session.getAttribute("user");
    }
}
