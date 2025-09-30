package robonaut.data.exceptions;

/**
 * An exception thrown when a task's description is empty in the Robonaut application.
 * Indicates that a task (e.g., ToDo, Deadline, or Event) lacks a required description.
 */
public class EmptyDescriptionException extends RobonautException {
    /**
     * Constructs an EmptyDescriptionException with a message indicating the task type with the empty description.
     *
     * @param taskType The type of task (e.g., "todo", "deadline", "event") that has an empty description.
     */
    public EmptyDescriptionException(String taskType) {
        super("OOPS!!! The description of a " + taskType + " cannot be empty.");
    }
}