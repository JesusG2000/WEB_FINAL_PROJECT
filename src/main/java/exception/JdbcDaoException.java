package exception;

public class JdbcDaoException extends Exception {


    public JdbcDaoException() {
        super();
    }

    public JdbcDaoException(String message) {
        super(message);
    }

    public JdbcDaoException(Exception e) {
        super(e);
    }

    public JdbcDaoException(String message, Exception e) {
        super(message, e);
    }
}
