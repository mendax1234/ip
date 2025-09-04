import java.util.*;

public class Robonaut {
    // Level 0
    public static void hello() {
        String logo = " ____       _                             _   \n"
                + "|  _ \\ ___ | |__   ___  _ __   __ _ _   _| |_ \n"
                + "| |_) / _ \\| '_ \\ / _ \\| '_ \\ / _` | | | | __|\n"
                + "|  _ < (_) | |_) | (_) | | | | (_| | |_| | |_ \n"
                + "|_| \\_\\___/|_.__/ \\___/|_| |_|\\__,_|\\__,_|\\__|\n";
        System.out.println(logo);
        System.out.println("Hey bro! Good to see you here! I'm Robonaut, your most intelligent helper!");
        System.out.println("What can I do for you?");
    }

    public static void bye() {
        System.out.println("Bye! Hope to see you again soon!");
    }

    // Level 1
    public static void echo(String s) {
        System.out.println("------------------------------------------------------------");
        System.out.println(s);
        System.out.println("------------------------------------------------------------");
    }

    // Level 2 & 3
    public static void add(ArrayList<Task> tasks, String content) {
        Task newTask = new Task(content);
        tasks.add(newTask);
        System.out.println("------------------------------------------------------------");
        System.out.println("added: " + content);
        System.out.println("------------------------------------------------------------");
    }

    public static void list(ArrayList<Task> tasks) {
        System.out.println("------------------------------------------------------------");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        System.out.println("------------------------------------------------------------");
    }

    public static void mark(ArrayList<Task> tasks, int index) {
        Task t = tasks.get(index);
        t.markAsDone();
        System.out.println("------------------------------------------------------------");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + t);
        System.out.println("------------------------------------------------------------");
    }

    public static void unmark(ArrayList<Task> tasks, int index) {
        Task t = tasks.get(index);
        t.markAsNotDone();
        System.out.println("------------------------------------------------------------");
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + t);
        System.out.println("------------------------------------------------------------");
    }

    public static String getStatus(boolean status) {
        StringBuilder s = new StringBuilder();
        s.append('[');
        if (status) {
            s.append('X');
            s.append(']');
        } else {
            s.append(' ');
            s.append(']');
        }
        return s.toString();
    }

    private static int extractNumber(String s) {
        // Split by space and take last token
        String[] parts = s.split(" ");
        if (parts.length == 1) {
            return 0;
        }
        return Integer.parseInt(parts[parts.length - 1]);
    }

    private static String extractOption(String s) {
        // Split by space and take last token
        String[] parts = s.split(" ");
        return parts[0];
    }

    public static void main(String[] args) {
        hello();

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        String option = sc.nextLine();

        while (!option.equals("bye")) {
            if (option.equals("list")) {
                list(tasks);
            } else if (option.startsWith("mark")) {
                int index = extractNumber(option);
                mark(tasks, index - 1);
            } else if (option.startsWith("unmark")) {
                int index = extractNumber(option);
                unmark(tasks, index - 1);
            } else {
                add(tasks, option);
            }
            option = sc.nextLine();
        }
        bye();
    }
}
