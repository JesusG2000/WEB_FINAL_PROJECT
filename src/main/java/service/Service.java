package service;

import exception.ServiceException;

public interface Service<T> {

    void create(T t) throws ServiceException;

    T readById(int id) throws ServiceException;

    T update(T t) throws ServiceException;

    void deleteById(int id) throws ServiceException;
}
