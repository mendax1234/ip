package robonaut.commands;

public class ListCommand extends Command {
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
