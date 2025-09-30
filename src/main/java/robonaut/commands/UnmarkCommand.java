package robonaut.commands;

import robonaut.data.exceptions.InvalidTaskNumberException;

/**
 * Represents a command to mark a task as not done in the task list based on the provided task number.
 */
public class UnmarkCommand extends Command {
    /** The full command string containing the unmark instruction and task number. */
    private String fullCommand;

    /**
     * Constructs an UnmarkCommand with the specified command string.
     *
     * @param fullCommand The full command string, expected to include the task number to unmark.
     */
    public UnmarkCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Executes the unmark command by marking the specified task as not done in the task list.
     *
     * @return A CommandResult containing a success message and the unmarked task, or an error message if the task number is invalid.
     */
    @Override
    public CommandResult execute() {
        try {
            int index = extractTaskIndex();
            data.getTask(index).markAsNotDone();
            return new CommandResult("OK, I've marked this task as not done yet:",
                    data.getTask(index));
        } catch (InvalidTaskNumberException e) {
            return new CommandResult(e.getMessage());
        }
    }

    /**
     * Extracts the task index from the command string.
     * The index is derived from the task number provided in the command, adjusted to zero-based indexing.
     *
     * @return The zero-based index of the task to unmark.
     * @throws InvalidTaskNumberException If the task number is missing, invalid, or out of range.
     */
    private int extractTaskIndex() throws InvalidTaskNumberException {
        String[] parts = fullCommand.split("\\s+");

        if (parts.length < 2) {
            throw new InvalidTaskNumberException("Please specify which task to mark/unmark. Example: mark 1");
        }

        try {
            int taskNumber = Integer.parseInt(parts[1]);

            if (taskNumber <= 0) {
                throw new InvalidTaskNumberException("Task numbers start from 1. Please use a positive number.");
            }

            if (taskNumber > data.size()) {
                if (data.isEmpty()) {
                    throw new InvalidTaskNumberException("There are no tasks in your list yet!");
                } else {
                    throw new InvalidTaskNumberException("Task number " + taskNumber + " does not exist. You only have " + data.size() + " task(s).");
                }
            }

            return taskNumber - 1;
        } catch (NumberFormatException e) {
            throw new InvalidTaskNumberException("'" + parts[1] + "' is not a valid task number. Please use a number like: mark 1");
        }
    }
}
