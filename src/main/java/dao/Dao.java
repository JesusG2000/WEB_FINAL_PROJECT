package dao;

import exception.JdbcDaoException;

public interface Dao<T> {

    void create(T t) throws JdbcDaoException;

    T readById(int id) throws JdbcDaoException;

    T update(T t) throws JdbcDaoException;

    void deleteById(int id) throws JdbcDaoException;
}
