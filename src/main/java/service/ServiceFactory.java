package service;

import service.impl.UserServiceImpl;

public final class ServiceFactory {

    private static UserService service = new UserServiceImpl();

    public static UserService getCalcService() {
        return service;
    }
}
