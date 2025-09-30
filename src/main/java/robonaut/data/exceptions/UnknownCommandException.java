package robonaut.data.exceptions;

/**
 * An exception thrown when an unrecognized or unknown command is provided in the Robonaut application.
 * Indicates that the command input does not match any known command.
 */
public class UnknownCommandException extends RobonautException {
    /**
     * Constructs an UnknownCommandException with a message indicating the unrecognized command.
     *
     * @param command The command string that was not recognized.
     */
    public UnknownCommandException(String command) {
        super("OOPS!!! I'm sorry, but I don't know what '" + command + "' means :-(");
    }
}