package tasks;

public class Deadline extends Task {
    /** The deadline time as a string. */
    protected String by;

    /**
     * Constructs a Deadline with the given description and due time.
     *
     * @param description the task description
     * @param by the deadline time
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String getTypeTag() {
        return "D";
    }

    @Override
    public String toString() {
        return "[" + getTypeTag() + "]" + getStatusIcon()
                + " " + description + " (by: " + by + ")";
    }
}
