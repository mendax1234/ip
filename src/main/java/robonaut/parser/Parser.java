package robonaut.parser;

import robonaut.commands.*;

/**
 * Parses user input to create appropriate command objects for the Robonaut application.
 * Converts input strings into specific command instances based on predefined command keywords.
 */
public class Parser {
    /**
     * Parses the input string to create a corresponding Command object.
     * Recognizes commands such as list, mark, unmark, todo, deadline, event, delete, find, and bye.
     * Returns an IncorrectCommand for invalid or unrecognized input.
     *
     * @param input The user input string to parse.
     * @return A Command object corresponding to the input, or an IncorrectCommand if the input is invalid or unrecognized.
     */
    public Command parseCommand(String input) {
        try {
            if (input.isEmpty()) {
                return new IncorrectCommand("Please enter a command!");
            }

            String lowerInput = input.toLowerCase();

            if (lowerInput.equals("list")) {
                return new ListCommand();
            } else if (lowerInput.startsWith("mark")) {
                return new MarkCommand(input);
            } else if (lowerInput.startsWith("unmark")) {
                return new UnmarkCommand(input);
            } else if (lowerInput.startsWith("todo")) {
                return new AddCommand(input);
            } else if (lowerInput.startsWith("deadline")) {
                return new AddCommand(input);
            } else if (lowerInput.startsWith("event")) {
                return new AddCommand(input);
            } else if (lowerInput.startsWith("delete")) {
                return new DeleteCommand(input);
            } else if (lowerInput.startsWith("find")) {
                return new FindCommand(input);
            } else if (lowerInput.equals("bye")) {
                return new ExitCommand();
            } else {
                String firstWord = input.split("\\s+")[0];
                return new IncorrectCommand("Unknown command: " + firstWord);
            }
        } catch (Exception e) {
            return new IncorrectCommand(e.getMessage());
        }
    }
}