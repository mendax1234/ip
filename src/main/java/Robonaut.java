public class Robonaut {
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

    public static void main(String[] args) {
        // Greetings
        hello();

        // Bye
        bye();
    }
}
