package robonaut.parser;

import robonaut.commands.*;

public class Parser {
    public Command parseCommand(String input) {
        try {
            if (input.isEmpty()) {
                return new IncorrectCommand("Please enter a command!");
            }

            String lowerInput = input.toLowerCase();

            if (lowerInput.equals("list")) {
                return new ListCommand();
            } else if (lowerInput.startsWith("mark ")) {
                return new MarkCommand(input);
            } else if (lowerInput.startsWith("unmark ")) {
                return new UnmarkCommand(input);
            } else if (lowerInput.startsWith("todo ")) {
                return new AddCommand(input);
            } else if (lowerInput.startsWith("deadline ")) {
                return new AddCommand(input);
            } else if (lowerInput.startsWith("event ")) {
                return new AddCommand(input);
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