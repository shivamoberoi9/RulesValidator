package main.java.rules;

import main.java.constants.ValidationFailureReasons;

import java.util.regex.Pattern;

public class PersonalNumberSpecialCharactersValidationValidationRule<T> implements ValidationRule<T> {
    private final int minLength;
    private final int maxLength;
    private final String allowedCharacters;

    public PersonalNumberSpecialCharactersValidationValidationRule(int minLength, int maxLength, String allowedCharacters) {
        this.minLength = minLength;
        this.maxLength = maxLength;
        this.allowedCharacters = allowedCharacters;
    }

    @Override
    public boolean apply(T input) {
        String personalNumberRegex = String.format("\\d{%d,%d}%s?\\d*", minLength, maxLength, allowedCharacters);
        return Pattern.matches(personalNumberRegex, input.toString());
    }

    @Override
    public String getFailureReason() {
        return ValidationFailureReasons.INVALID_SPECIAL_CHARACTERS;
    }
}

