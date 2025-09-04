import java.util.ArrayList;
import java.util.Scanner;

public class Robonaut {
    /** The horizontal line used to create box output when printing */
    private static final String HORIZONTAL_LINE = "------------------------------------------------------------";

    public static void main(String[] args) {
        hello();

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        String option = sc.nextLine();
        option = option.toLowerCase();

        while (!option.equals("bye")) {
            if (option.equals("list")) {
                list(tasks);
            } else if (option.startsWith("mark")) {
                int index = extractInteger(option);
                mark(tasks, index - 1);
            } else if (option.startsWith("unmark")) {
                int index = extractInteger(option);
                unmark(tasks, index - 1);
            } else {
                add(tasks, option);
            }
            option = sc.nextLine();
        }
        bye();

        sc.close();
    }

    /**
     * Greets the user
     */
    public static void hello() {
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
     * Ends the conversation
     */
    public static void bye() {
        System.out.println("Bye! Hope to see you again soon!");
    }

    /**
     * Adds a task to the task list
     * @param tasks Task list
     * @param content Description of the task to be added
     */
    public static void add(ArrayList<Task> tasks, String content) {
        Task newTask = new Task(content);
        tasks.add(newTask);
        System.out.println(HORIZONTAL_LINE);
        System.out.println("added: " + content);
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Lists all the current tasks in the task list
     * @param tasks Task list
     */
    public static void list(ArrayList<Task> tasks) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Marks a specific task in the task list as Done
     * @param tasks Task list
     * @param index Index of the task to be marked as Done
     */
    public static void mark(ArrayList<Task> tasks, int index) {
        Task t = tasks.get(index);
        t.markAsDone();
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + t);
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Unmarks a specific task in the task list as NotDone
     * @param tasks Task list
     * @param index Index of the task to be marked as NotDone
     */
    public static void unmark(ArrayList<Task> tasks, int index) {
        Task t = tasks.get(index);
        t.markAsNotDone();
        System.out.println(HORIZONTAL_LINE);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + t);
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Extract the integer appears after the string from a string
     * @param s String containing strings and ints
     * @return Integer extract from s
     */
    private static int extractInteger(String s) {
        // Split by space and take last token
        String[] parts = s.split(" ");
        if (parts.length == 1) {
            return 0;
        }
        return Integer.parseInt(parts[parts.length - 1]);
    }
}
