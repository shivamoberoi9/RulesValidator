package test.java.rules;

import main.java.rules.PersonalNumberSpecialCharactersValidationValidationRule;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PersonalNumberSpecialCharactersValidationValidationRuleTest {

    @Test
    public void testValidSpecialChar() {
        var rule = new PersonalNumberSpecialCharactersValidationValidationRule<>(6, 8, "+-");
        assertTrue(rule.apply("19900125-5570"));
    }

    @Test
    public void testInvalidSpecialChar() {
        var rule = new PersonalNumberSpecialCharactersValidationValidationRule<>(6, 8, "+-");
        assertFalse(rule.apply("19900125*5570"));
    }
}
