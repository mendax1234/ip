import java.util.ArrayList;
import java.util.Scanner;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

public class Robonaut {
    /** The horizontal line used to create box output when printing */
    private static final String HORIZONTAL_LINE = "------------------------------------------------------------";

    // Constants for command prefix lengths
    private static final int TODO_PREFIX_LENGTH = 5;  // Length of "todo"
    private static final int DEADLINE_PREFIX_LENGTH = 9;  // Length of "deadline"
    private static final int EVENT_PREFIX_LENGTH = 6;  // Length of "event"

    public static void main(String[] args) {
        printHelloMessage();
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        String option = sc.nextLine();
        option = option.toLowerCase();

        while (!option.equals("bye")) {
            if (option.equals("list")) {
                printListCommand(tasks);
            } else if (option.startsWith("mark")) {
                int index = extractInteger(option);
                executeMarkCommand(tasks, index - 1);
            } else if (option.startsWith("unmark")) {
                int index = extractInteger(option);
                executeUnmarkCommand(tasks, index - 1);
            } else if (option.startsWith("todo")) {
                String desc = option.substring(TODO_PREFIX_LENGTH); // skip "todo"
                printAddCommand(tasks, new ToDo(desc));
            } else if (option.startsWith("deadline")) {
                String[] parts = option.substring(DEADLINE_PREFIX_LENGTH).split("/by", 2);
                printAddCommand(tasks, new Deadline(parts[0].trim(), parts[1].trim()));
            } else if (option.startsWith("event")) {
                String[] parts = option.substring(EVENT_PREFIX_LENGTH).split("/from|/to");
                String desc = parts[0].trim();
                String from = parts[1].trim();
                String to = parts[2].trim();
                printAddCommand(tasks, new Event(desc, from, to));
            } else {
                System.out.println("Unknown command!");
            }
            option = sc.nextLine();
        }
        printByeMessage();

        sc.close();
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
     * @param task the task to add
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
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
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
     * Extracts the task number (1-based) from a command string.
     *
     * @param s the input string
     * @return the 0-based task index
     */
    private static int extractInteger(String s) {
        // Split by space and take last token
        String[] parts = s.split(" ");
        if (parts.length == 1) {
            return 0;
        }
        return Integer.parseInt(parts[parts.length - 1]);
    }

    /**
     * Prints a Horizontal Line
     */
    private static void printHorizontalLine() {
        System.out.println(HORIZONTAL_LINE);
    }
}
