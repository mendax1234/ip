package robonaut.commands;

import robonaut.data.TaskList;

public abstract class Command {
    protected TaskList data;

    public void setData(TaskList data) {
        this.data = data;
    }

    public abstract CommandResult execute();

    public boolean isExit() {
        return false;
    }
}