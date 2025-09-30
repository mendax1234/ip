package robonaut.data.tasks;

/**
 * Represents a task with a specific start and end time in the Robonaut application.
 * Extends the Task class to include a start time and an end time for the event.
 */
public class Event extends Task {
    /** Start time of the event. */
    protected String from;

    /** End time of the event. */
    protected String to;

    /**
     * Constructs an Event with the given description, start, and end times.
     *
     * @param description the task description
     * @param from the start time
     * @param to the end time
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the type tag for this task, indicating it is an Event task.
     *
     * @return The string "E" representing the Event task type.
     */
    @Override
    public String getTypeTag() {
        return "E";
    }

    /**
     * Serializes the Event task into a string format for storage.
     * Includes the task's serialized data, start time, and end time, separated by delimiters.
     *
     * @return A string representation of the task for storage.
     */
    @Override
    public String serialize() {
        return super.serialize() + " | " + from + " | " + to;
    }

    /**
     * Returns a user-friendly string representation of the Event task.
     * Includes the task type, status icon, description, start time, and end time.
     *
     * @return A string in the format "[E][status] description (from: start_time to: end_time)".
     */
    @Override
    public String toString() {
        return "[" + getTypeTag() + "]" + getStatusIcon()
                + " " + description + " (from: " + from + " to: " + to + ")";
    }
}
