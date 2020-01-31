package service;

public interface UserService {
    boolean isRegistered(String name) ;

    boolean isExist(String username, String password) ;
}
