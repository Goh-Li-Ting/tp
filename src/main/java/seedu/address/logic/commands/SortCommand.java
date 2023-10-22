package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEPARTMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALARY;

import java.util.Comparator;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Sorts the list that is displayed to the user.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_SUCCESS = "List successfully sorted.";
    public static final String MESSAGE_MULTIPLE_PREFIXES = "Sorting by multiple prefixes is not supported.";

    public static final String MESSAGE_USAGE = "Sorts the list displayed based on the prefix provided."
            + "\n" + "Parameters: "
            + "[" + PREFIX_ID + "] " + "or " + "[" + PREFIX_DEPARTMENT + "] " + "or " + "[" + PREFIX_EMAIL + "] "
            + "or " + "[" + PREFIX_SALARY + "] " + "or " + "[" + PREFIX_ROLE + "] "
            + "or " + "[" + PREFIX_NAME + "] " + "or " + "[" + PREFIX_PHONE + "] "
            + "\n"

            + "Examples: " + "\n" + COMMAND_WORD + " " + PREFIX_ID
            + "\n" + COMMAND_WORD + " " + PREFIX_NAME;

    private final Comparator<Person> comparator;

    public SortCommand(Comparator<Person> comparator) {
        this.comparator = comparator;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateSortedPersonList(comparator);
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SortCommand)) {
            return false;
        }

        SortCommand otherFindCommand = (SortCommand) other;
        return comparator.equals(otherFindCommand.comparator);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("comparator", comparator)
                .toString();
    }
}
