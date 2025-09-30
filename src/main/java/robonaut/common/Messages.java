package robonaut.common;

/**
 * Utility class containing user-visible messages and constants shared across the Robonaut application.
 * This class provides static constants for command prefix lengths and user interface messages.
 */
public class Messages {
    /**
     * The ASCII art logo displayed to the user when the Robonaut application starts.
     */
    public static final String MESSAGE_LOGO = """
                 ____       _                             _  \s
                |  _ \\ ___ | |__   ___  _ __   __ _ _   _| |_\s
                | |_) / _ \\| '_ \\ / _ \\| '_ \\ / _` | | | | __|
                |  _ < (_) | |_) | (_) | | | | (_| | |_| | |_\s
                |_| \\_\\___/|_.__/ \\___/|_| |_|\\__,_|\\__,_|\\__|
                """;
    /**
     * The horizontal line used to separate messages in the user interface.
     */
    public static final String HORIZONTAL_LINE = "------------------------------------------------------------";

    /**
     * The length of the "todo" prefix in command strings, including the space after "todo".
     */
    public static final int TODO_PREFIX_LENGTH = 4;

    /**
     * The length of the "deadline" prefix in command strings, including the space after "deadline".
     */
    public static final int DEADLINE_PREFIX_LENGTH = 8;

    /**
     * The length of the "event" prefix in command strings, including the space after "event".
     */
    public static final int EVENT_PREFIX_LENGTH = 5;
}
