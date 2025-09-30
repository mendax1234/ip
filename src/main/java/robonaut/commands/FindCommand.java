package robonaut.commands;

import robonaut.data.tasks.Task;

import java.util.List;

/**
 * Represents a command to find tasks in the task list that match a specified keyword in their description.
 * The search is case-insensitive and returns a formatted list of matching tasks.
 */
public class FindCommand extends Command {
    /** The full command string containing the find instruction and the keyword to search for. */
    private final String fullCommand;

    /**
     * Constructs a FindCommand with the specified command string.
     *
     * @param fullCommand The full command string, expected to start with "find" followed by a keyword.
     */
    public FindCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Executes the find command by searching for tasks whose descriptions contain the specified keyword.
     * Returns a formatted list of matching tasks or a message indicating no matches were found.
     *
     * @return A CommandResult containing either a formatted string of matching tasks or a message indicating no matches.
     */
    @Override
    public CommandResult execute() {
        String keyword = fullCommand.substring(5).trim();

        List<Task> matching = data.getTasks().stream()
                .filter(task -> task.getDescription().toLowerCase().contains(keyword.toLowerCase()))
                .toList();

        if (matching.isEmpty()) {
            return new CommandResult("No matching tasks found for: " + keyword);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < matching.size(); i++) {
            sb.append((i + 1)).append(".").append(matching.get(i)).append("\n");
        }

        return new CommandResult(sb.toString().trim());
    }
}
