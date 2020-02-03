package servlet;

import bean.User;

import javax.servlet.http.HttpServletRequest;

public interface PageAccess {
    User isLogin(HttpServletRequest request);
}
