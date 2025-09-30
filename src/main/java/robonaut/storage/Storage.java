package robonaut.storage;

import robonaut.data.TaskList;
import robonaut.data.tasks.Task;
import robonaut.data.tasks.ToDo;
import robonaut.data.tasks.Deadline;
import robonaut.data.tasks.Event;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Manages the persistence of tasks in the Robonaut application by reading from and writing to a file.
 * Handles loading tasks into a TaskList and saving tasks from a TaskList to the specified file path.
 */
public class Storage {
    // TODO: Add format not support
    /** The file path where tasks are stored. */
    private final String filePath;

    /**
     * Constructs a Storage instance with the specified file path for task persistence.
     *
     * @param filePath The path to the file where tasks will be read from and written to.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file specified by the file path into a TaskList.
     * Creates the parent directory if it does not exist. If the file does not exist or an error occurs,
     * an empty TaskList is returned.
     *
     * @return A TaskList containing the tasks loaded from the file, or an empty TaskList if the file does not exist or an error occurs.
     */
    public TaskList load() {
        Path path = Paths.get(filePath);

        try {
            Files.createDirectories(path.getParent());
        } catch (IOException e) {
            System.out.println("Could not create data folder: " + e.getMessage());
        }

        if (!Files.exists(path)) {
            return new TaskList();
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            TaskList tasks = new TaskList();
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = parseLine(line); // deserialize
                if (task != null) {
                    tasks.addTask(task);
                }
            }
            return tasks;
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return new TaskList();
        }
    }

    /**
     * Saves the tasks from the specified TaskList to the file.
     * Each task is serialized and written to a new line in the file.
     * Prints an error message to the console if saving fails.
     *
     * @param tasks The TaskList containing the tasks to save.
     */
    public void save(TaskList tasks) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : tasks.getTasks()) {
                writer.write(formatTask(task)); // serialize
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    /**
     * Formats a task into a string representation for file storage.
     *
     * @param task The Task object to serialize.
     * @return A string containing the serialized task data.
     */
    private String formatTask(Task task) {
        return task.serialize();
    }

    /**
     * Parses a line from the file to create a Task object.
     * Supports ToDo, Deadline, and Event task types based on the type tag in the line.
     * Returns null if the line is corrupted or the task type is unsupported.
     *
     * @param line The line from the file to parse.
     * @return A Task object if parsing is successful, or null if the line is invalid or corrupted.
     */
    private Task parseLine(String line) {
        try {
            String[] parts = line.split(" \\| ");
            String type = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];

            Task task;
            switch (type) {
            case "T":
                task = new ToDo(description);
                break;
            case "D":
                task = new Deadline(description, parts[3]);
                break;
            case "E":
                task = new Event(description, parts[3], parts[4]);
                break;
            default:
                return null; // corrupted line
            }

            if (isDone) {
                task.markAsDone();
            }
            return task;
        } catch (Exception e) {
            return null; // corrupted format
        }
    }
}
