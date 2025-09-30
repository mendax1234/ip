package robonaut.ui;

import static robonaut.common.Messages.HORIZONTAL_LINE;
import static robonaut.common.Messages.MESSAGE_LOGO;

import java.util.Scanner;
import robonaut.commands.CommandResult;
import robonaut.data.tasks.Task;

/**
 * Handles user interface interactions for the Robonaut application.
 * Provides methods for displaying messages, reading user input, and formatting command results.
 */
public class Ui {
    /** The scanner used to read user input from the console. */
    private Scanner scanner;

    /**
     * Constructs a Ui instance, initializing the scanner for reading user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message and application logo to the user.
     */
    public void showWelcome() {
        System.out.println(MESSAGE_LOGO);
        System.out.println("Hey bro! Good to see you here! I'm Robonaut, your most intelligent helper!");
        System.out.println("What can I do for you?");
    }

    /**
     * Displays a farewell message to the user.
     */
    public void showBye() {
        System.out.println("Hope to see you again soon!");
    }

    /**
     * Reads a command from the user via console input.
     *
     * @return The trimmed command string entered by the user.
     */
    public String readCommand() {
        return scanner.nextLine().trim();
    }

    /**
     * Displays the result of a command to the user, including feedback, the relevant task (if any),
     * and the total number of tasks (if applicable).
     *
     * @param result The CommandResult containing the feedback, task, and task count to display.
     */
    public void showResultToUser(CommandResult result) {
        showLine();
        System.out.println(result.getFeedbackToUser());

        Task task = result.getRelevantTask();
        if (task != null) {
            System.out.println("  " + task);
            if (result.getTotalTasks() > 0) {
                System.out.println("Now you have " + result.getTotalTasks() + " tasks in the list.");
            }
        }
        showLine();
    }

    /**
     * Prints a horizontal line to separate messages in the user interface.
     */
    private void showLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Closes the scanner used for reading user input.
     */
    public void close() {
        scanner.close();
    }
}
