package net.antonyho.musify;

public class DataRetrievalException extends Exception {
    public DataRetrievalException(String message) {
        super(message);
    }

    public DataRetrievalException(String message, Throwable cause) {
        super(message, cause);
    }
}
