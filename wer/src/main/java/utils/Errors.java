package utils;

public class Errors extends RuntimeException {
    public Errors(Throwable cause) {
        super(cause);
    }

    public void notEmpty(String str, String message) {
        if ("".equals(str) || str == null) {
            throw new IllegalArgumentException(message);
        }
    }

    public void notNull(Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }
}
