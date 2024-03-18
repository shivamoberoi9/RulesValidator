package test.java.rules;

import main.java.rules.NonNullOrBlankValidationValidationRule;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NonNullOrBlankValidationValidationRuleTest {

    @Test
    public void testValidNotBlank() {
        var rule = new NonNullOrBlankValidationValidationRule<>();
        assertTrue(rule.apply("John Doe"));
    }

    @Test
    public void testInvalidNotBlank() {
        var rule = new NonNullOrBlankValidationValidationRule<>();
        assertFalse(rule.apply(""));
    }
}
