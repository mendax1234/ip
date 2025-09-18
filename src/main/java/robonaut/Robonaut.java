package robonaut;

import robonaut.commands.Command;
import robonaut.commands.CommandResult;
import robonaut.commands.ExitCommand;
import robonaut.data.TaskList;
import robonaut.parser.Parser;
import robonaut.ui.Ui;

/**
 * Entry point of the Robonaut application.
 * Initializes the application and starts the interaction with the user.
 */
public class Robonaut {

    private Ui ui;
    private TaskList taskList = new TaskList();

    public static void main(String[] args) {
        new Robonaut().run();
    }

    /** Runs the program until termination. */
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
        ui.showWelcome();
    }

    /** Prints the goodbye message and exits. */
    private void exit() {
        ui.showBye();
        ui.close();
    }

    /** Reads the user command and executes it, until the user issues the exit command. */
    private void runCommandLoopUntilExitCommand() {
        Command command;
        do {
            String userCommandText = ui.readCommand();
            command = new Parser().parseCommand(userCommandText);
            CommandResult result = executeCommand(command);
            ui.showResultToUser(result);
        } while (!ExitCommand.isExit(command));
    }

    /**
     * Executes the command and returns the result.
     *
     * @param command user command
     * @return result of the command
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