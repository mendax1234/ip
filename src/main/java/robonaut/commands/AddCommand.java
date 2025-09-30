package robonaut.commands;

import static robonaut.common.Messages.DEADLINE_PREFIX_LENGTH;
import static robonaut.common.Messages.EVENT_PREFIX_LENGTH;
import static robonaut.common.Messages.TODO_PREFIX_LENGTH;

import robonaut.data.exceptions.EmptyDescriptionException;
import robonaut.data.exceptions.InvalidFormatException;
import robonaut.data.exceptions.RobonautException;
import robonaut.data.tasks.*;

/**
 * Represents a command to add a task (ToDo, Deadline, or Event) to the task list.
 * The command parses the input string to determine the type of task and its details.
 */
public class AddCommand extends Command {
    /** The full command string containing the add instruction and description of the task to add. */
    private String fullCommand;

    /**
     * Constructs an AddCommand with the specified command string.
     *
     * @param fullCommand The full command string containing the task type and details.
     */
    public AddCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Executes the add command by determining the task type (todo, deadline, or event)
     * and adding the corresponding task to the task list.
     *
     * @return A CommandResult containing the result message, the added task (if any), and the task list size.
     */
    @Override
    public CommandResult execute() {
        try {
            String lowerCommand = fullCommand.toLowerCase();

            if (lowerCommand.startsWith("todo")) {
                return addTodo();
            } else if (lowerCommand.startsWith("deadline")) {
                return addDeadline();
            } else if (lowerCommand.startsWith("event")) {
                return addEvent();
            }

            return new CommandResult("Unknown add command type");
        } catch (Exception e) {
            return new CommandResult(e.getMessage());
        }
    }

    /**
     * Adds a ToDo task based on the command string.
     *
     * @return A CommandResult with the success message, the added task, and the task list size.
     * @throws EmptyDescriptionException If the task description is empty.
     */
    private CommandResult addTodo() throws EmptyDescriptionException {
        if (fullCommand.length() <= TODO_PREFIX_LENGTH) {
            throw new EmptyDescriptionException("todo");
        }

        String desc = fullCommand.substring(TODO_PREFIX_LENGTH).trim();
        if (desc.isEmpty()) {
            throw new EmptyDescriptionException("todo");
        }

        Task task = new ToDo(desc);
        data.addTask(task);
        return new CommandResult("Got it. I've added this task:", task, data.size());
    }

    /**
     * Adds a Deadline task based on the command string, including a description and due date.
     *
     * @return A CommandResult with the success message, the added task, and the task list size.
     * @throws RobonautException If the description is empty or the format is invalid.
     */
    private CommandResult addDeadline() throws RobonautException {
        if (fullCommand.length() <= DEADLINE_PREFIX_LENGTH) {
            throw new EmptyDescriptionException("deadline");
        }

        String remaining = fullCommand.substring(DEADLINE_PREFIX_LENGTH).trim();
        if (remaining.isEmpty()) {
            throw new EmptyDescriptionException("deadline");
        }

        if (!remaining.contains("/by")) {
            throw new InvalidFormatException("Deadline format should be: deadline <description> /by <date>");
        }

        String[] parts = remaining.split("/by", 2);
        String desc = parts[0].trim();
        String by = parts[1].trim();

        if (desc.isEmpty()) {
            throw new EmptyDescriptionException("deadline");
        }
        if (by.isEmpty()) {
            throw new InvalidFormatException("The deadline date cannot be empty. Use format: deadline <description> /by <date>");
        }

        Task task = new Deadline(desc, by);
        data.addTask(task);
        return new CommandResult("Got it. I've added this task:", task, data.size());
    }

    /**
     * Adds an Event task based on the command string, including a description, start time, and end time.
     *
     * @return A CommandResult with the success message, the added task, and the task list size.
     * @throws RobonautException If the description is empty or the format is invalid.
     */
    private CommandResult addEvent() throws RobonautException {
        if (fullCommand.length() <= EVENT_PREFIX_LENGTH) {
            throw new EmptyDescriptionException("event");
        }

        String remaining = fullCommand.substring(EVENT_PREFIX_LENGTH).trim();
        if (remaining.isEmpty()) {
            throw new EmptyDescriptionException("event");
        }

        if (!remaining.contains("/from") || !remaining.contains("/to")) {
            throw new InvalidFormatException("Event format should be: event <description> /from <start> /to <end>");
        }

        String[] parts = remaining.split("/from|/to");
        if (parts.length != 3) {
            throw new InvalidFormatException("Event format should be: event <description> /from <start> /to <end>");
        }

        String desc = parts[0].trim();
        String from = parts[1].trim();
        String to = parts[2].trim();

        if (desc.isEmpty()) {
            throw new EmptyDescriptionException("event");
        }
        if (from.isEmpty()) {
            throw new InvalidFormatException("The event start time cannot be empty. Use format: event <description> /from <start> /to <end>");
        }
        if (to.isEmpty()) {
            throw new InvalidFormatException("The event end time cannot be empty. Use format: event <description> /from <start> /to <end>");
        }

        Task task = new Event(desc, from, to);
        data.addTask(task);
        return new CommandResult("Got it. I've added this task:", task, data.size());
    }
}
