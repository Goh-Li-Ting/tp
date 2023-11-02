package seedu.address.logic.commands;

import java.io.IOException;
import java.nio.file.Path;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.storage.JsonAddressBookStorage;

/**
 * Exports the data file into the specified path.
 */
public class ExportCommand extends Command {

    public static final String COMMAND_WORD = "export";
    public static final String MESSAGE_SUCCESS = "Data successfully exported";
    public static final String MESSAGE_FAILURE = "Data could not be exported";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Exports the data file to the specified location. "
            + "\n"
            + "Parameters: "
            + "FILE_PATH"
            + "\n"
            + "Example: " + COMMAND_WORD + " " + "./mydata/persons.json";

    private final Path filePath;

    public ExportCommand(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        try {
            new JsonAddressBookStorage(filePath).saveAddressBook(model.getAddressBook(), filePath);
        } catch (IOException ex) {
            throw new CommandException(MESSAGE_FAILURE + ": " + ex.getMessage());
        }
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ExportCommand)) {
            return false;
        }

        ExportCommand otherExportCommand = (ExportCommand) other;
        return filePath.equals(otherExportCommand.filePath);
    }
}