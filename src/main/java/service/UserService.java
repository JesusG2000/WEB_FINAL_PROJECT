package service;

import exception.ServiceException;

public interface UserService {
    boolean isRegistered(String name) ;

    boolean isLogin(String username, String password) ;
}
