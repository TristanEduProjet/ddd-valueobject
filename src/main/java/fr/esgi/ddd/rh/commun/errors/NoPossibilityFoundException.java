package fr.esgi.ddd.rh.commun.errors;

public class NoPossibilityFoundException extends Exception {
    public NoPossibilityFoundException() {
        super();
    }

    public NoPossibilityFoundException(final String message) {
        super(message);
    }

    public NoPossibilityFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public NoPossibilityFoundException(final Throwable cause) {
        super(cause);
    }

    public NoPossibilityFoundException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
