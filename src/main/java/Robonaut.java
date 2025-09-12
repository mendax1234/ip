import java.util.ArrayList;
import java.util.Scanner;

import exceptions.RobonautException;
import exceptions.EmptyDescriptionException;
import exceptions.InvalidTaskNumberException;
import exceptions.UnknownCommandException;
import exceptions.InvalidFormatException;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

public class Robonaut {
    /**
     * The horizontal line used to create box output when printing
     */
    private static final String HORIZONTAL_LINE = "------------------------------------------------------------";

    // Constants for command prefix lengths
    private static final int TODO_PREFIX_LENGTH = 4;  // Length of "todo"
    private static final int DEADLINE_PREFIX_LENGTH = 8;  // Length of "deadline"
    private static final int EVENT_PREFIX_LENGTH = 5;  // Length of "event"

    public static void main(String[] args) {
        printHelloMessage();
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        String option = sc.nextLine();

        while (!option.trim().equalsIgnoreCase("bye")) {
            try {
                processCommand(option.trim(), tasks);
            } catch (RobonautException e) {
                printError(e.getMessage());
            }
            option = sc.nextLine();
        }
        printByeMessage();
        sc.close();
    }

    /**
     * Processes the user command and executes the appropriate action.
     *
     * @param command the user input command
     * @param tasks   the task list
     * @throws RobonautException if there's an error in command processing
     */
    private static void processCommand(String command, ArrayList<Task> tasks) throws RobonautException {
        if (command.isEmpty()) {
            throw new UnknownCommandException("");
        }

        String lowerCommand = command.toLowerCase();

        if (lowerCommand.equals("list")) {
            printListCommand(tasks);
        } else if (lowerCommand.startsWith("mark")) {
            handleMarkCommand(command, tasks);
        } else if (lowerCommand.startsWith("unmark")) {
            handleUnmarkCommand(command, tasks);
        } else if (lowerCommand.startsWith("todo")) {
            handleTodoCommand(command, tasks);
        } else if (lowerCommand.startsWith("deadline")) {
            handleDeadlineCommand(command, tasks);
        } else if (lowerCommand.startsWith("event")) {
            handleEventCommand(command, tasks);
        } else {
            // Extract the first word as the command for better error message
            String firstWord = command.split("\\s+")[0];
            throw new UnknownCommandException(firstWord);
        }
    }

    /**
     * Handles the todo command with error checking.
     *
     * @param command the full command string
     * @param tasks   the task list
     * @throws EmptyDescriptionException if description is empty
     */
    private static void handleTodoCommand(String command, ArrayList<Task> tasks) throws EmptyDescriptionException {
        if (command.length() <= TODO_PREFIX_LENGTH) {
            throw new EmptyDescriptionException("todo");
        }

        String desc = command.substring(TODO_PREFIX_LENGTH).trim();
        if (desc.isEmpty()) {
            throw new EmptyDescriptionException("todo");
        }

        printAddCommand(tasks, new ToDo(desc));
    }

    /**
     * Handles the deadline command with error checking.
     *
     * @param command the full command string
     * @param tasks   the task list
     * @throws EmptyDescriptionException if description is empty
     * @throws InvalidFormatException    if format is incorrect
     */
    private static void handleDeadlineCommand(String command, ArrayList<Task> tasks) throws RobonautException {
        if (command.length() <= DEADLINE_PREFIX_LENGTH) {
            throw new EmptyDescriptionException("deadline");
        }

        String remaining = command.substring(DEADLINE_PREFIX_LENGTH).trim();
        if (remaining.isEmpty()) {
            throw new EmptyDescriptionException("deadline");
        }

        if (!remaining.contains("/by")) {
            throw new InvalidFormatException("Deadline format should be: deadline <description> /by <date>");
        }

        String[] parts = remaining.split("/by", 2);
        if (parts.length != 2) {
            throw new InvalidFormatException("Deadline format should be: deadline <description> /by <date>");
        }

        String desc = parts[0].trim();
        String by = parts[1].trim();

        if (desc.isEmpty()) {
            throw new EmptyDescriptionException("deadline");
        }

        if (by.isEmpty()) {
            throw new InvalidFormatException("The deadline date cannot be empty. Use format: deadline <description> /by <date>");
        }

        printAddCommand(tasks, new Deadline(desc, by));
    }

    /**
     * Handles the event command with error checking.
     *
     * @param command the full command string
     * @param tasks   the task list
     * @throws EmptyDescriptionException if description is empty
     * @throws InvalidFormatException    if format is incorrect
     */
    private static void handleEventCommand(String command, ArrayList<Task> tasks) throws RobonautException {
        if (command.length() <= EVENT_PREFIX_LENGTH) {
            throw new EmptyDescriptionException("event");
        }

        String remaining = command.substring(EVENT_PREFIX_LENGTH).trim();
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

        printAddCommand(tasks, new Event(desc, from, to));
    }

    /**
     * Handles the mark command with error checking.
     *
     * @param command the full command string
     * @param tasks   the task list
     * @throws InvalidTaskNumberException if task number is invalid
     */
    private static void handleMarkCommand(String command, ArrayList<Task> tasks) throws InvalidTaskNumberException {
        int index = extractTaskIndex(command, tasks);
        executeMarkCommand(tasks, index);
    }

    /**
     * Handles the unmark command with error checking.
     *
     * @param command the full command string
     * @param tasks   the task list
     * @throws InvalidTaskNumberException if task number is invalid
     */
    private static void handleUnmarkCommand(String command, ArrayList<Task> tasks) throws InvalidTaskNumberException {
        int index = extractTaskIndex(command, tasks);
        executeUnmarkCommand(tasks, index);
    }

    /**
     * Extracts and validates the task index from mark/unmark commands.
     *
     * @param command the full command string
     * @param tasks   the task list
     * @return the valid 0-based task index
     * @throws InvalidTaskNumberException if task number is invalid
     */
    private static int extractTaskIndex(String command, ArrayList<Task> tasks) throws InvalidTaskNumberException {
        String[] parts = command.split("\\s+");

        if (parts.length < 2) {
            throw new InvalidTaskNumberException("Please specify which task to mark/unmark. Example: mark 1");
        }

        try {
            int taskNumber = Integer.parseInt(parts[1]);

            if (taskNumber <= 0) {
                throw new InvalidTaskNumberException("Task numbers start from 1. Please use a positive number.");
            }

            if (taskNumber > tasks.size()) {
                if (tasks.isEmpty()) {
                    throw new InvalidTaskNumberException("There are no tasks in your list yet!");
                } else {
                    throw new InvalidTaskNumberException("Task number " + taskNumber + " does not exist. You only have " + tasks.size() + " task(s).");
                }
            }

            return taskNumber - 1; // Convert to 0-based index
        } catch (NumberFormatException e) {
            throw new InvalidTaskNumberException("'" + parts[1] + "' is not a valid task number. Please use a number like: mark 1");
        }
    }

    /**
     * Prints error messages with proper formatting.
     *
     * @param message the error message to print
     */
    private static void printError(String message) {
        printHorizontalLine();
        System.out.println(" " + message);
        printHorizontalLine();
    }

    /**
     * Prints the greeting message with logo.
     */
    public static void printHelloMessage() {
        String logo = """
                 ____       _                             _  \s
                |  _ \\ ___ | |__   ___  _ __   __ _ _   _| |_\s
                | |_) / _ \\| '_ \\ / _ \\| '_ \\ / _` | | | | __|
                |  _ < (_) | |_) | (_) | | | | (_| | |_| | |_\s
                |_| \\_\\___/|_.__/ \\___/|_| |_|\\__,_|\\__,_|\\__|
                """;
        System.out.println(logo);
        System.out.println("Hey bro! Good to see you here! I'm Robonaut, your most intelligent helper!");
        System.out.println("What can I do for you?");
    }

    /**
     * Prints the goodbye message.
     */
    public static void printByeMessage() {
        System.out.println("Bye! Hope to see you again soon!");
    }

    /**
     * Adds a task to the task list and prints confirmation.
     *
     * @param tasks the task list
     * @param task  the task to add
     */
    public static void printAddCommand(ArrayList<Task> tasks, Task task) {
        tasks.add(task);
        printHorizontalLine();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        printHorizontalLine();
    }

    /**
     * Prints all tasks in the list.
     *
     * @param tasks the task list
     */
    public static void printListCommand(ArrayList<Task> tasks) {
        printHorizontalLine();
        if (tasks.isEmpty()) {
            System.out.println("Your task list is empty! Add some tasks to get started.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
        printHorizontalLine();
    }

    /**
     * Marks the task at the given index as done.
     *
     * @param tasks the task list
     * @param index the index of the task (0-based)
     */
    public static void executeMarkCommand(ArrayList<Task> tasks, int index) {
        Task t = tasks.get(index);
        t.markAsDone();
        printHorizontalLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + t);
        printHorizontalLine();
    }

    /**
     * Marks the task at the given index as not done.
     *
     * @param tasks the task list
     * @param index the index of the task (0-based)
     */
    public static void executeUnmarkCommand(ArrayList<Task> tasks, int index) {
        Task t = tasks.get(index);
        t.markAsNotDone();
        printHorizontalLine();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + t);
        printHorizontalLine();
    }

    /**
     * Prints a Horizontal Line
     */
    private static void printHorizontalLine() {
        System.out.println(HORIZONTAL_LINE);
    }
}
