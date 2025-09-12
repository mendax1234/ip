package robonaut.exceptions;

public class UnknownCommandException extends RobonautException {
    public UnknownCommandException(String command) {
        super("OOPS!!! I'm sorry, but I don't know what '" + command + "' means :-(");
    }
}