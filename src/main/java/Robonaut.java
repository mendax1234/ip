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

    public static void main(String[] args) {
        // Greetings
        hello();
        Scanner sc = new Scanner (System.in);
        String option = sc.nextLine();
        while (!option.equals("bye")) {
            echo(option);
            option = sc.nextLine();
        }

        // Bye
        bye();
    }
}
