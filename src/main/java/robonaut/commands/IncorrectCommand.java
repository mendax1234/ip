package robonaut.commands;

/**
 * Represents a command that handles invalid or unrecognized input in the Robonaut application.
 * This command returns an error message to the user indicating why the input was incorrect.
 */
public class IncorrectCommand extends Command {
    /** The error message to be returned to the user. */
    private String errorMessage;

    /**
     * Constructs an IncorrectCommand with the specified error message.
     *
     * @param errorMessage The error message to be displayed to the user.
     */
    public IncorrectCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Executes the command by returning a CommandResult containing the error message.
     *
     * @return A CommandResult containing the error message provided during construction.
     */
    @Override
    public CommandResult execute() {
        return new CommandResult(errorMessage);
    }
}
