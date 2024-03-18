package main.java;

import main.java.exceptions.ValidationException;
import main.java.rules.ValidationRule;


public class ValidatorFactory {
    @SafeVarargs
    public static <T> Validator<T> createValidator(ValidationRule<T>... validationRules) {
        return input -> {
            for (ValidationRule<T> validationRule : validationRules) {
                if (!validationRule.apply(input)) {
                    String failureReason = validationRule.getFailureReason();
                    throw new ValidationException(failureReason);
                }
            }
            return true;
        };
    }
}