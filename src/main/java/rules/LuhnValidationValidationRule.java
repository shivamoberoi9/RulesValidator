package main.java.rules;

import main.java.constants.ValidationFailureReasons;


public class LuhnValidationValidationRule<T> implements ValidationRule<T> {

    private final int digits;

    public LuhnValidationValidationRule(int digits) {
        this.digits = digits;
    }


    @Override
    public boolean apply(T input) {
        String sanitizedInput = input.toString().replaceAll("[^0-9]", "");
        sanitizedInput = sanitizedInput.substring(sanitizedInput.length() - digits);
        return validateLuhn(sanitizedInput);
    }

    @Override
    public String getFailureReason() {
        return ValidationFailureReasons.INVALID_CHECKSUM;
    }

    private boolean validateLuhn(String sanitizedInput) {
        int sum = 0;
        boolean alternate = false;
        for (int i = sanitizedInput.length() - 1; i >= 0; i--) {
            int digit = sanitizedInput.charAt(i) - '0';
            if (alternate) {
                digit *= 2;
                if (digit > 9) {
                    digit = digit % 10 + 1;
                }
            }
            sum += digit;
            alternate = !alternate;
        }
        return sum % 10 == 0;
    }
}
