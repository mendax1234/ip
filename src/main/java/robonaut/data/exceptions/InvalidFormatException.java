package robonaut.data.exceptions;

/**
 * An exception thrown when a command or task input has an invalid format in the Robonaut application.
 * Indicates that the input does not conform to the expected format for a task or command.
 */
public class InvalidFormatException extends RobonautException {
    /**
     * Constructs an InvalidFormatException with the specified error message.
     *
     * @param message The detailed message explaining the format error.
     */
    public InvalidFormatException(String message) {
        super("OOPS!!! " + message);
    }
}