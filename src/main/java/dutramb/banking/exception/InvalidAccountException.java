package dutramb.banking.exception;

public class InvalidAccountException extends Exception {

    public InvalidAccountException(String customMessage) {
        super(customMessage);
    }

}
