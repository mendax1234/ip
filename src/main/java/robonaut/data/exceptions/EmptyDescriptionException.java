package robonaut.data.exceptions;

public class EmptyDescriptionException extends RobonautException {
    public EmptyDescriptionException(String taskType) {
        super("OOPS!!! The description of a " + taskType + " cannot be empty.");
    }
}