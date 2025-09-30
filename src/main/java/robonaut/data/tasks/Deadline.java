package robonaut.data.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A Task with a deadline.
 */
public class Deadline extends Task {
    /** The deadline as a LocalDate. */
    protected LocalDate by;

    /** Formatter for user-friendly output. */
    private static final DateTimeFormatter OUTPUT_FORMAT =
            DateTimeFormatter.ofPattern("MMM d yyyy"); // e.g., Oct 15 2019

    /**
     * Constructs a Deadline with the given description and due date.
     * @param description the task description
     * @param by the deadline in yyyy-MM-dd format
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by); // parse input string into LocalDate
    }

    @Override
    public String getTypeTag() {
        return "D";
    }

    @Override
    public String serialize() {
        // Store in yyyy-MM-dd format
        return super.serialize() + " | " + by.toString();
    }

    @Override
    public String toString() {
        // Print in user-friendly format
        return "[" + getTypeTag() + "]" + getStatusIcon()
                + " " + description + " (by: " + by.format(OUTPUT_FORMAT) + ")";
    }
}
