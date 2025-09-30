package robonaut.commands;

/**
 * Represents a command to list all tasks in the task list.
 * Returns a formatted string of all tasks or a message indicating the list is empty.
 */
public class ListCommand extends Command {
    /**
     * Executes the list command by retrieving all tasks from the task list and formatting them.
     *
     * @return A CommandResult containing either a formatted string of all tasks or a message indicating the task list is empty.
     */
    @Override
    public CommandResult execute() {
        if (data.isEmpty()) {
            return new CommandResult("Your task list is empty! Add some tasks to get started.");
        }

        StringBuilder result = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < data.size(); i++) {
            result.append((i + 1)).append(". ").append(data.getTask(i)).append("\n");
        }
        return new CommandResult(result.toString().trim());
    }
}
