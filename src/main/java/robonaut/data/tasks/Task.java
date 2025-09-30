package robonaut.data.tasks;

/**
 * An abstract base class for tasks in the Robonaut application.
 * Represents a task with a description and a completion status, serving as the parent class for specific task types
 * such as ToDo, Deadline, and Event.
 */
public abstract class Task {
    /** Description of the task */
    protected String description;

    /** Indicates whether the task is marked as done. */
    protected boolean isDone;

    /**
     * Constructs a Task with given description
     * By default, the task is not done.
     *
     * @param description Description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks this task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks this task as not done
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the status icon of this task.
     * @return "[X]" if done, "[ ]" otherwise
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public String getDescription() {
        return description;
    }

    public String serialize() {
        return getTypeTag() + " | " + (isDone ? 1 : 0) + " | " + description;
    }

    /**
     * Returns the type tag of the task (T, D, or E).
     *
     * @return the type tag
     */
    public abstract String getTypeTag();

    /**
     * Returns a string representation of the task.
     *
     * @return a string containing type tag, status icon, and description
     */
    @Override
    public String toString() {
        return "[" + getTypeTag() + "]" + getStatusIcon() + " " + description;
    }
}
