package robonaut;

import robonaut.commands.Command;
import robonaut.commands.CommandResult;
import robonaut.commands.ExitCommand;
import robonaut.data.TaskList;
import robonaut.parser.Parser;
import robonaut.storage.Storage;
import robonaut.ui.Ui;

/**
 * Entry point of the Robonaut application.
 * Initializes the application and starts the interaction with the user.
 */
public class Robonaut {
    /** The user interface for displaying messages and reading input. */
    private Ui ui;
    /** The task list containing all tasks managed by the application. */
    private TaskList taskList;
    /** The storage handler for loading and saving tasks to a file. */
    private Storage storage = new Storage("./data/robonaut.txt");

    /**
     * The main method to start the Robonaut application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Robonaut().run();
    }

    /**
     * Runs the Robonaut application until the user issues an exit command.
     * Initializes the application, processes user commands, and terminates gracefully.
     */
    public void run() {
        start();
        runCommandLoopUntilExitCommand();
        exit();
    }

    /**
     * Sets up the required objects, and prints the welcome message.
     */
    private void start() {
        this.ui = new Ui();
        this.taskList = storage.load(); // load tasks at startup
        ui.showWelcome();
    }

    /**
     * Displays the goodbye message and closes the user interface.
     */
    private void exit() {
        ui.showBye();
        ui.close();
    }

    /**
     * Runs a loop that reads, parses, and executes user commands until an exit command is received.
     * Saves the task list to storage after each command execution.
     */
    private void runCommandLoopUntilExitCommand() {
        Command command;
        do {
            String userCommandText = ui.readCommand();
            command = new Parser().parseCommand(userCommandText);
            CommandResult result = executeCommand(command);
            ui.showResultToUser(result);
            storage.save(taskList);
        } while (!ExitCommand.isExit(command));
    }

    /**
     * Executes the specified command and returns its result.
     * Sets the task list for the command and handles any exceptions during execution.
     *
     * @param command The command to execute.
     * @return A CommandResult containing the outcome of the command execution, or an error message if an exception occurs.
     */
    private CommandResult executeCommand(Command command) {
        try {
            command.setData(taskList);
            CommandResult result = command.execute();
            return result;
        } catch (Exception e) {
            return new CommandResult(e.getMessage());
        }
    }
}