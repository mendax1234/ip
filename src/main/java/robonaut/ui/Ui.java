package robonaut.ui;

import static common.Messages.MESSAGE_LOGO;

import java.util.Scanner;
import robonaut.commands.CommandResult;
import robonaut.data.tasks.Task;

public class Ui {
    private static final String HORIZONTAL_LINE = "------------------------------------------------------------";
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println(MESSAGE_LOGO);
        System.out.println("Hey bro! Good to see you here! I'm Robonaut, your most intelligent helper!");
        System.out.println("What can I do for you?");
    }

    public void showBye() {
        System.out.println("Hope to see you again soon!");
    }

    public String readCommand() {
        return scanner.nextLine().trim();
    }

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

    private void showLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    public void close() {
        scanner.close();
    }
}
