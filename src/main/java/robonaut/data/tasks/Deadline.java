package robonaut.data.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline in the Robonaut application.
 * Extends the Task class to include a due date, stored as a LocalDate.
 */
public class Deadline extends Task {
    /** The deadline date for the task. */
    protected LocalDate by;

    /** Formatter for displaying dates in a user-friendly format (e.g., Oct 15 2019). */
    private static final DateTimeFormatter OUTPUT_FORMAT =
            DateTimeFormatter.ofPattern("MMM d yyyy"); // e.g., Oct 15 2019

    /**
     * Constructs a Deadline task with the specified description and due date.
     *
     * @param description The description of the task.
     * @param by The deadline date in yyyy-MM-dd format (e.g., 2019-10-15).
     * @throws java.time.format.DateTimeParseException If the date format is invalid.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    /**
     * Returns the type tag for this task, indicating it is a Deadline task.
     *
     * @return The string "D" representing the Deadline task type.
     */
    @Override
    public String getTypeTag() {
        return "D";
    }

    /**
     * Serializes the Deadline task into a string format for storage.
     * Includes the task's serialized data and the deadline in yyyy-MM-dd format.
     *
     * @return A string representation of the task for storage.
     */
    @Override
    public String serialize() {
        // Store in yyyy-MM-dd format (machine-readable)
        return super.serialize() + " | " + by.toString();
    }

    /**
     * Returns a user-friendly string representation of the Deadline task.
     * Includes the task type, status icon, description, and formatted deadline.
     *
     * @return A string in the format "[D][status] description (by: MMM d yyyy)".
     */
    @Override
    public String toString() {
        // Print in user-friendly format
        return "[" + getTypeTag() + "]" + getStatusIcon()
                + " " + description + " (by: " + by.format(OUTPUT_FORMAT) + ")";
    }
}
