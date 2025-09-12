package robonaut.tasks;

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
