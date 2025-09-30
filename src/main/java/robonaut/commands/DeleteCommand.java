package robonaut.commands;

import robonaut.data.exceptions.InvalidTaskNumberException;
import robonaut.data.tasks.Task;

/**
 * Represents a command to delete a task from the task list based on the provided task number.
 */
public class DeleteCommand extends Command {
    /** The full command string containing the delete instruction and task number. */
    private String fullCommand;

    /**
     * Constructs a DeleteCommand with the specified command string.
     *
     * @param fullCommand The full command string, expected to include the task number to delete.
     */
    public DeleteCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Executes the delete command by removing the specified task from the task list.
     *
     * @return A CommandResult containing the success message, the deleted task, and the updated task list size,
     *         or an error message if the task number is invalid.
     */
    @Override
    public CommandResult execute() {
        try {
            int index = extractTaskIndex();
            Task deletedTask = data.deleteTask(index);
            return new CommandResult("Noted. I've removed this task:",
                    deletedTask, data.size());
        } catch (InvalidTaskNumberException e) {
            return new CommandResult(e.getMessage());
        }
    }

    /**
     * Extracts the task index from the command string.
     * The index is derived from the task number provided in the command, adjusted to zero-based indexing.
     *
     * @return The zero-based index of the task to delete.
     * @throws InvalidTaskNumberException If the task number is missing, invalid, or out of range.
     */
    private int extractTaskIndex() throws InvalidTaskNumberException {
        String[] parts = fullCommand.split("\\s+");

        if (parts.length < 2) {
            throw new InvalidTaskNumberException("Please specify which task to delete. Example: delete 1");
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
            throw new InvalidTaskNumberException("'" + parts[1] + "' is not a valid task number. Please use a number like: delete 1");
        }
    }
}