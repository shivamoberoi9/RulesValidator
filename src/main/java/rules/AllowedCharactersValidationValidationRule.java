package main.java.rules;

import main.java.constants.ValidationFailureReasons;

import java.util.regex.Pattern;

public class AllowedCharactersValidationValidationRule<T> implements ValidationRule<T> {

    private final Pattern pattern;

    public AllowedCharactersValidationValidationRule(String allowedCharacters) {
        this.pattern = Pattern.compile(allowedCharacters);
    }

    @Override
    public boolean apply(T input) {
        return pattern.matcher(input.toString()).matches();
    }

    @Override
    public String getFailureReason() {
        return ValidationFailureReasons.INVALID_SPECIAL_CHARACTERS;
    }
}
