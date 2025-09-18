package robonaut.data.tasks;

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

    @Override
    public String getTypeTag() {
        return "E";
    }

    @Override
    public String toString() {
        return "[" + getTypeTag() + "]" + getStatusIcon()
                + " " + description + " (from: " + from + " to: " + to + ")";
    }
}
