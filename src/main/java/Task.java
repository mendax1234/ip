public class Task {
    /** Description of the task */
    private final String description;

    /** Completion status of the task */
    private boolean isDone;

    /**
     * Create a Task with a specified description
     * its status is set to false
     *
     * @param description Description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as Done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmarks the task
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Gets the Status Icon of the task
     * @return the staus Icon of the task
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Displays a String that contains the status and
     * the description of the task
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }
}