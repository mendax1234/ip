# Robonaut User Guide

Robonaut is a desktop task management application optimized for use via a Command Line Interface (CLI). It allows you to manage tasks efficiently, including ToDo tasks, deadlines, and events, with features to add, list, mark, unmark, delete, and search tasks.

- [Quick Start](#quick-start)
- [Features](#features)
- [FAQ](#faq)
- [Known Issues](#known-issues)
- [Command Summary](#command-summary)

## Quick Start

1. Ensure you have **Java 17** or above installed on your computer.
2. Download the latest `robonaut.jar` file from [releases](https://github.com/{your-username}/{repo-name}/releases).
3. Copy the `robonaut.jar` file to the folder you want to use as the home folder for Robonaut.
4. Open a command terminal, navigate to the folder containing the jar file using `cd`, and run the application with:
   ```bash
   java -jar robonaut.jar
   ```
5. The application will start, displaying a welcome message with the Robonaut logo. Type commands in the console and press Enter to execute them.
6. Example commands to try:
    - `list`: Lists all tasks.
    - `todo Buy groceries`: Adds a ToDo task.
    - `deadline Submit report /by 2025-12-31`: Adds a deadline task.
    - `delete 1`: Deletes the first task in the list.
    - `bye`: Exits the application.

Refer to the [Features](#features) section below for details on each command.

## Features

> **Note**:
> - Words in `UPPER_CASE` are parameters to be supplied by the user (e.g., in `todo DESCRIPTION`, `DESCRIPTION` is the task description).
> - Items in square brackets are optional (e.g., `/by DATE` in `deadline` is required, but the date format is flexible).
> - Parameters can be in any order where applicable.
> - Extraneous parameters for commands that do not take parameters (e.g., `list`, `bye`) are ignored.
> - Commands are case-insensitive (e.g., `TODO`, `todo`, or `ToDo` are equivalent).

### Viewing the task list: `list`

Shows a list of all tasks in the task list, including their type, status, and details.

**Format**: `list`

**Example**:
```
list
```
Output:
```
Here are the tasks in your list:
1. [T][ ] Buy groceries
2. [D][X] Submit report (by: Dec 31 2025)
```

### Adding a ToDo task: `todo`

Adds a simple task without a deadline or time frame.

**Format**: `todo DESCRIPTION`

**Example**:
```
todo Buy groceries
```
Output:
```
Got it. I've added this task:
  [T][ ] Buy groceries
Now you have 1 task in the list.
```

### Adding a Deadline task: `deadline`

Adds a task with a due date.

**Format**: `deadline DESCRIPTION /by DATE`

- `DATE` should be in `yyyy-MM-dd` format (e.g., `2025-12-31`).

**Example**:
```
deadline Submit report /by 2025-12-31
```
Output:
```
Got it. I've added this task:
  [D][ ] Submit report (by: Dec 31 2025)
Now you have 2 tasks in the list.
```

### Adding an Event task: `event`

Adds a task with a start and end time.

**Format**: `event DESCRIPTION /from START_TIME /to END_TIME`

- `START_TIME` and `END_TIME` can be any string (e.g., `2pm`, `14:00`, `Monday`).

**Example**:
```
event Team meeting /from 2pm /to 4pm
```
Output:
```
Got it. I've added this task:
  [E][ ] Team meeting (from: 2pm to: 4pm)
Now you have 3 tasks in the list.
```

### Marking a task as done: `mark`

Marks a task as completed based on its index in the task list.

**Format**: `mark INDEX`

- `INDEX` is the task number shown in the `list` command (a positive integer).

**Example**:
```
mark 1
```
Output:
```
Nice! I've marked this task as done:
  [T][X] Buy groceries
```

### Marking a task as not done: `unmark`

Marks a task as not completed based on its index.

**Format**: `unmark INDEX`

- `INDEX` is the task number shown in the `list` command (a positive integer).

**Example**:
```
unmark 1
```
Output:
```
OK, I've marked this task as not done yet:
  [T][ ] Buy groceries
```

### Deleting a task: `delete`

Removes a task from the task list based on its index.

**Format**: `delete INDEX`

- `INDEX` is the task number shown in the `list` command (a positive integer).

**Example**:
```
delete 1
```
Output:
```
Noted. I've removed this task:
  [T][ ] Buy groceries
Now you have 2 tasks in the list.
```

### Finding tasks by keyword: `find`

Searches for tasks whose descriptions contain the specified keyword (case-insensitive).

**Format**: `find KEYWORD`

**Example**:
```
find report
```
Output:
```
Here are the matching tasks in your list:
1. [D][ ] Submit report (by: Dec 31 2025)
```

### Exiting the program: `bye`

Exits the Robonaut application.

**Format**: `bye`

**Example**:
```
bye
```
Output:
```
Hope to see you again soon!
```

### Saving the data

Robonaut data is saved automatically to `./data/robonaut.txt` after any command that modifies the task list (e.g., `todo`, `deadline`, `event`, `mark`, `unmark`, `delete`). There is no need to save manually.

### Editing the data file

Robonaut data is stored in a text file at `./data/robonaut.txt`. Advanced users can edit this file directly, but caution is advised:
- The file format is specific (e.g., `T | 0 | description` for ToDo tasks).
- Invalid formats may cause data loss, as Robonaut will start with an empty task list if the file is corrupted.
- Always back up the file before editing.

## FAQ

**Q**: How do I transfer my data to another computer?  
**A**: Install Robonaut on the new computer, then copy the `./data/robonaut.txt` file from the original computer to the same folder as the `robonaut.jar` file on the new computer.

**Q**: What date format is required for `deadline` tasks?  
**A**: Use `yyyy-MM-dd` (e.g., `2025-12-31`). The output will be displayed in a user-friendly format like `Dec 31 2025`.

**Q**: Can I use any format for event times?  
**A**: Yes, `event` start and end times can be any string (e.g., `2pm`, `Monday`), but ensure they are meaningful for your reference.

## Known Issues

- If the `./data/robonaut.txt` file is manually edited with an invalid format, Robonaut will start with an empty task list on the next run. Always back up the file before editing.
- The application does not validate the format of `event` start and end times, so ensure they are clear to avoid confusion.

## Command Summary

| Action           | Format, Examples                                                                                    |
|------------------|-----------------------------------------------------------------------------------------------------|
| **List**         | `list`                                                                                              |
| **Add ToDo**     | `todo DESCRIPTION` <br> e.g., `todo Buy groceries`                                                  |
| **Add Deadline** | `deadline DESCRIPTION /by DATE` <br> e.g., `deadline Submit report /by 2025-12-31`                  |
| **Add Event**    | `event DESCRIPTION /from START_TIME /to END_TIME` <br> e.g., `event Team meeting /from 2pm /to 4pm` |
| **Mark**         | `mark INDEX` <br> e.g., `mark 1`                                                                    |
| **Unmark**       | `unmark INDEX` <br> e.g., `unmark 1`                                                                |
| **Delete**       | `delete INDEX` <br> e.g., `delete 1`                                                                |
| **Find**         | `find KEYWORD` <br> e.g., `find report`                                                             |
| **Exit**         | `bye`                                                                                               |