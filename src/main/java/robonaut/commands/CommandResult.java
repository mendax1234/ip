package robonaut.commands;

import robonaut.data.tasks.Task;

/**
 * Represents the result of executing a command in the Robonaut application.
 * Contains feedback for the user, an optional relevant task, and the total number of tasks in the task list.
 */
public class CommandResult {
    /** The feedback message to be displayed to the user. */
    private final String feedbackToUser;
    /** The task associated with the command result, if any. */
    private final Task relevantTask;
    /** The total number of tasks in the task list after the command execution. */
    private final int totalTasks;

    /**
     * Constructs a CommandResult with only a feedback message.
     *
     * @param feedbackToUser The message to be displayed to the user.
     */
    public CommandResult(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
        this.relevantTask = null;
        this.totalTasks = 0;
    }

    /**
     * Constructs a CommandResult with a feedback message and a relevant task.
     *
     * @param feedbackToUser The message to be displayed to the user.
     * @param relevantTask   The task associated with the command result.
     */
    public CommandResult(String feedbackToUser, Task relevantTask) {
        this.feedbackToUser = feedbackToUser;
        this.relevantTask = relevantTask;
        this.totalTasks = 0;
    }

    /**
     * Constructs a CommandResult with a feedback message, a relevant task, and the total number of tasks.
     *
     * @param feedbackToUser The message to be displayed to the user.
     * @param relevantTask   The task associated with the command result.
     * @param totalTasks     The total number of tasks in the task list.
     */
    public CommandResult(String feedbackToUser, Task relevantTask, int totalTasks) {
        this.feedbackToUser = feedbackToUser;
        this.relevantTask = relevantTask;
        this.totalTasks = totalTasks;
    }

    /**
     * Retrieves the feedback message for the user.
     *
     * @return The feedback message.
     */
    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    /**
     * Retrieves the task associated with the command result.
     *
     * @return The relevant task, or {@code null} if no task is associated.
     */
    public Task getRelevantTask() {
        return relevantTask;
    }

    /**
     * Retrieves the total number of tasks in the task list.
     *
     * @return The total number of tasks.
     */
    public int getTotalTasks() {
        return totalTasks;
    }
}