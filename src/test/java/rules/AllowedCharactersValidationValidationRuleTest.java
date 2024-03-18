package test.java.rules;

import main.java.rules.AllowedCharactersValidationValidationRule;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AllowedCharactersValidationValidationRuleTest {

    @Test
    public void testValidSpecialChar() {
        var rule = new AllowedCharactersValidationValidationRule<>("åäöÅÄÖ");
        assertTrue(rule.apply("Jönköping Ångström"));
    }

    @Test
    public void testInvalidSpecialChar() {
        var rule = new AllowedCharactersValidationValidationRule<>("åäöÅÄÖ");
        assertFalse(rule.apply("John!Doe"));
    }
}
