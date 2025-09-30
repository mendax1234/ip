package robonaut.commands;

import robonaut.data.TaskList;

/**
 * An abstract base class for all commands in the Robonaut application.
 * Provides a common structure for commands that operate on a TaskList.
 */
public abstract class Command {
    /** The TaskList instance that this command operates on. */
    protected TaskList data;

    /**
     * Sets the TaskList instance for this command to operate on.
     *
     * @param data The TaskList to be used by this command.
     */
    public void setData(TaskList data) {
        this.data = data;
    }

    /**
     * Executes the command and returns the result.
     *
     * @return A CommandResult containing the outcome of the command execution.
     */
    public abstract CommandResult execute();

    /**
     * Determines if this command is an exit command that terminates the application.
     *
     * @return {@code true} if the command is an exit command, {@code false} otherwise.
     */
    public boolean isExit() {
        return false;
    }
}