package robonaut.data.exceptions;

/**
 * An exception thrown when an invalid task number is provided in the Robonaut application.
 * Indicates that the task number is either non-numeric, out of range, or otherwise invalid.
 */
public class InvalidTaskNumberException extends RobonautException {
    /**
     * Constructs an InvalidTaskNumberException with the specified error message.
     *
     * @param message The detailed message explaining the invalid task number error.
     */
    public InvalidTaskNumberException(String message) {
        super("OOPS!!! " + message);
    }
}