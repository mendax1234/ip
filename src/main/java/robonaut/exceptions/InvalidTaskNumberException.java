package robonaut.exceptions;

public class InvalidTaskNumberException extends RobonautException {
    public InvalidTaskNumberException(String message) {
        super("OOPS!!! " + message);
    }
}