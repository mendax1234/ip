package robonaut.data;

import java.util.ArrayList;
import robonaut.data.tasks.Task;

/**
 * Represents a list of tasks in the Robonaut application.
 * Manages a collection of Task objects, providing methods to add, remove, retrieve, and query the task list.
 */
public class TaskList {
    /** The internal list storing Task objects. */
    private ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The Task object to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes and returns the task at the specified index.
     *
     * @param index The zero-based index of the task to remove.
     * @return The Task object that was removed.
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    public Task deleteTask(int index) {
        return tasks.remove(index);
    }

    /**
     * Retrieves the task at the specified index.
     *
     * @param index The zero-based index of the task to retrieve.
     * @return The Task object at the specified index.
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the entire list of tasks.
     *
     * @return An ArrayList containing all Task objects in the task list.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Checks if the task list is empty.
     *
     * @return {@code true} if the task list is empty, {@code false} otherwise.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }
}