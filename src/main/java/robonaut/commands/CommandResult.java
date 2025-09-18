package robonaut.commands;

import robonaut.data.tasks.Task;

public class CommandResult {
    private final String feedbackToUser;
    private final Task relevantTask;
    private final int totalTasks;

    public CommandResult(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
        this.relevantTask = null;
        this.totalTasks = 0;
    }

    public CommandResult(String feedbackToUser, Task relevantTask) {
        this.feedbackToUser = feedbackToUser;
        this.relevantTask = relevantTask;
        this.totalTasks = 0;
    }

    public CommandResult(String feedbackToUser, Task relevantTask, int totalTasks) {
        this.feedbackToUser = feedbackToUser;
        this.relevantTask = relevantTask;
        this.totalTasks = totalTasks;
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public Task getRelevantTask() {
        return relevantTask;
    }

    public int getTotalTasks() {
        return totalTasks;
    }
}