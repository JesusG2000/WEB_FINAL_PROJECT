package service;

import bean.User;

public interface UserService {
    boolean isRegistered(User user) ;

    boolean isExist(User user) ;
}
