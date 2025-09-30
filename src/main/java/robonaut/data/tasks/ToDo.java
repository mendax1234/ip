package robonaut.data.tasks;

/**
 * Represents a simple task without a deadline or specific time frame in the Robonaut application.
 * Extends the Task class to provide a basic task with a description and completion status.
 */
public class ToDo extends Task {
    /**
     * Constructs a Todo with the given description.
     *
     * @param description the task description
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String getTypeTag() {
        return "T";
    }
}
