package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a person's remark in the address book.
 * Guarantees: immutable; accepts any non-null string, including an empty one.
 */
public class Remark {

    public final String value;

    /**
     * Constructs a {@code Remark}.
     */
    public Remark(String remark) {
        requireNonNull(remark);
        value = remark;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Remark otherRemark)) {
            return false;
        }

        return value.equals(otherRemark.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
