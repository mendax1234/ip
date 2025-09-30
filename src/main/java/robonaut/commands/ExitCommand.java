package robonaut.commands;

/**
 * Represents a command to exit the Robonaut application.
 * This command terminates the application and returns a farewell message.
 */
public class ExitCommand extends Command {
    /**
     * Executes the exit command, returning a farewell message.
     *
     * @return A CommandResult containing the farewell message "Goodbye!".
     */
    @Override
    public CommandResult execute() {
        return new CommandResult("Goodbye!");
    }

    /**
     * Indicates that this command is an exit command, signaling the application to terminate.
     *
     * @return {@code true} to indicate this is an exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Checks if the provided command is an instance of ExitCommand.
     *
     * @param command The command to check.
     * @return {@code true} if the command is an ExitCommand, {@code false} otherwise.
     */
    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }
}