package robonaut.data.exceptions;

/**
 * A base exception class for custom exceptions in the Robonaut application.
 * Serves as the parent class for specific exceptions related to task and command processing.
 */
public class RobonautException extends Exception {
    /**
     * Constructs a RobonautException with the specified error message.
     *
     * @param message The detailed message explaining the error.
     */
    public RobonautException(String message) {
        super(message);
    }
}
