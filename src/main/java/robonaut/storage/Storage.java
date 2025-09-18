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

public class Storage {
    // TODO: Add format not support
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /** Load tasks from file into a TaskList */
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

    /** Save TaskList to file */
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

    private String formatTask(Task task) {
        return task.serialize();
    }

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
