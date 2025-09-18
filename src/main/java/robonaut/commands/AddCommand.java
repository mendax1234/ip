package robonaut.commands;

import robonaut.data.exceptions.EmptyDescriptionException;
import robonaut.data.exceptions.InvalidFormatException;
import robonaut.data.exceptions.RobonautException;
import robonaut.data.tasks.*;

public class AddCommand extends Command {
    private static final int TODO_PREFIX_LENGTH = 4;
    private static final int DEADLINE_PREFIX_LENGTH = 8;
    private static final int EVENT_PREFIX_LENGTH = 5;

    private String fullCommand;

    public AddCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

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
