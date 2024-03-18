package main.java.rules;

import main.java.constants.ValidationFailureReasons;

import java.util.Arrays;

public class DigitsLengthValidationValidationRule<T> implements ValidationRule<T> {
    private final int[] lengths;

    public DigitsLengthValidationValidationRule(int... lengths) {
        this.lengths = lengths;
    }

    @Override
    public boolean apply(T input) {
        String sanitizedInput = input.toString().replaceAll("[^\\d]", "");
        return Arrays.stream(lengths).anyMatch(length -> sanitizedInput.length() == length);
    }

    @Override
    public String getFailureReason() {
        return ValidationFailureReasons.INVALID_LENGTH;
    }
}
