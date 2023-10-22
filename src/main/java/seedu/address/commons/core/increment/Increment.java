package seedu.address.commons.core.increment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an increment.
 */
public class Increment {
    public static final String MESSAGE_CONSTRAINTS = "Increment should only contain numbers and an optional "
            + "negative sign in front of the numbers and have at most 2 decimals";
    public static final String VALIDATION_REGEX = "^-?[0-9]+(\\.[0-9]{1,2})?$";
    private double increment;

    /**
     * Constructs a {@code Increment}
     *
     * @param increment A valid increment.
     */
    public Increment(String increment) {
        requireNonNull(increment);
        checkArgument(isValidIncrement(increment), MESSAGE_CONSTRAINTS);
        this.increment = Double.parseDouble(increment);
    }

    /**
     * Returns true if a given string is a valid increment.
     */
    public static boolean isValidIncrement(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns the value of the increment.
     */
    public double getValue() {
        return increment;
    }

    @Override
    public String toString() {
        return String.format("%.2f", increment);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Increment)) {
            return false;
        }

        Increment otherIncrement = (Increment) other;
        return increment == otherIncrement.increment;
    }
}