import java.lang.reflect.Array;
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

    // Level 2
    public static void add(ArrayList<String> list, String content) {
        list.add(content);
        System.out.println("------------------------------------------------------------");
        System.out.println("added: " + content);
        System.out.println("------------------------------------------------------------");
    }

    public static void list(ArrayList<String> list) {
        int i = 1;
        System.out.println("------------------------------------------------------------");
        for (String e : list) {
            System.out.println(i + ". " + e);
            i++;
        }
        System.out.println("------------------------------------------------------------");
    }

    public static void main(String[] args) {
        // Greetings
        hello();

        Scanner sc = new Scanner (System.in);

        // Level-2
        String option = sc.nextLine();
        ArrayList<String> list = new ArrayList<>();
        while (!option.equals("bye")) {
            switch (option) {
            case "list":
                list(list);
                break;
            default:
                add(list, option);
                break;
            }
            option = sc.nextLine();
        }
    }
}
