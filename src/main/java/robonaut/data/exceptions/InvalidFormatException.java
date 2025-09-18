package robonaut.data.exceptions;

public class InvalidFormatException extends RobonautException {
    public InvalidFormatException(String message) {
        super("OOPS!!! " + message);
    }
}