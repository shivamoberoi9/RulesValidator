package test.java.rules;

import main.java.rules.DigitsLengthValidationValidationRule;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class DigitsLengthValidationValidationRuleTest {

    @Test
    public void testValidLength() {
        var rule = new DigitsLengthValidationValidationRule<>(12, 10);
        assertTrue(rule.apply("19900125-5570"));
    }

    @Test
    public void testInvalidLength() {
        var rule = new DigitsLengthValidationValidationRule<>(12, 10);
        assertFalse(rule.apply("19900125-557"));
    }
}
