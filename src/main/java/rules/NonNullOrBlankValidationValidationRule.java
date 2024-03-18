package main.java.rules;

import main.java.constants.ValidationFailureReasons;

public class NonNullOrBlankValidationValidationRule<T> implements ValidationRule<T> {
    @Override
    public boolean apply(T input) {
        return (input != null && !input.toString().trim().isEmpty());
    }
    @Override
    public String getFailureReason() {
        return ValidationFailureReasons.INVALID_INPUT;
    }
}
