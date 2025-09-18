package robonaut.commands;

import robonaut.data.exceptions.InvalidTaskNumberException;

public class UnmarkCommand extends Command {
    private String fullCommand;

    public UnmarkCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

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
