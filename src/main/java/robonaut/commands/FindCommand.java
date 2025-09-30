package robonaut.commands;

import robonaut.data.tasks.Task;

import java.util.List;

public class FindCommand extends Command {
    private final String fullCommand;

    public FindCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

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
