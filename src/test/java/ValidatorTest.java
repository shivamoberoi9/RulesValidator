package test.java;

import main.java.ValidatorFactory;
import main.java.constants.ValidationConstants;
import main.java.exceptions.ValidationException;
import main.java.rules.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidatorTest {

    @Test
    public void validatePersonalNumber_Valid() throws ValidationException {
        var lengthRule = new DigitsLengthValidationValidationRule<>(ValidationConstants.SwedishPersonalNumber.MAX_LENGTH, ValidationConstants.SwedishPersonalNumber.MIN_LENGTH);
        var luhnRule = new LuhnValidationValidationRule<>(ValidationConstants.SwedishPersonalNumber.LUHN_DIGITS_LENGTH);
        var personalNumberSpecialCharRule = new PersonalNumberSpecialCharactersValidationValidationRule<>(ValidationConstants.SwedishPersonalNumber.SPECIAL_CHARACTER_AFTER_MIN_LENGTH, ValidationConstants.SwedishPersonalNumber.SPECIAL_CHARACTER_AFTER_MAX_LENGTH, ValidationConstants.SwedishPersonalNumber.SPECIAL_CHARACTERS);

        var personalNumberValidator = ValidatorFactory.createValidator(lengthRule, luhnRule, personalNumberSpecialCharRule);

        var validPersonalNumber = "19900125-5570";
        assertTrue(personalNumberValidator.validate(validPersonalNumber));
    }

    @Test
    public void validateName_Valid() throws ValidationException {
        var notBlankRule = new NonNullOrBlankValidationValidationRule<>();
        var specialCharNameRule = new AllowedCharactersValidationValidationRule<>(ValidationConstants.SwedishName.ALLOWED_CHARACTERS);

        var nameValidator = ValidatorFactory.createValidator(notBlankRule, specialCharNameRule);

        String validName = "Jönköping Ångström";
        assertTrue(nameValidator.validate(validName));
    }

    @Test
    public void validatePersonalNumber_InvalidSpecialChar() {
        var lengthRule = new DigitsLengthValidationValidationRule<>(10, 12);
        var luhnRule = new LuhnValidationValidationRule<>(10);
        var specialCharRule = new PersonalNumberSpecialCharactersValidationValidationRule<>(6, 8, "[+\\-]");

        var personalNumberValidator = ValidatorFactory.createValidator(lengthRule, luhnRule, specialCharRule);

        String invalidPersonalNumberWithSpecialChar = "19900125*5570"; // Invalid due to special character
        assertThrows(ValidationException.class, () -> personalNumberValidator.validate(invalidPersonalNumberWithSpecialChar));
    }

    @Test
    public void validatePersonalNumber_InvalidLength() {
        var lengthRule = new DigitsLengthValidationValidationRule<>(10, 12);
        var luhnRule = new LuhnValidationValidationRule<>(10);
        var specialCharRule = new PersonalNumberSpecialCharactersValidationValidationRule<>(6, 8, "[+\\-]");

        var personalNumberValidator = ValidatorFactory.createValidator(lengthRule, luhnRule, specialCharRule);

        String invalidPersonalNumberWithInvalidLength = "19900125"; // Invalid length
        assertThrows(ValidationException.class, () -> personalNumberValidator.validate(invalidPersonalNumberWithInvalidLength));
    }

    @Test
    public void validatePersonalNumber_InvalidChecksum() {
        var lengthRule = new DigitsLengthValidationValidationRule<>(10, 12);
        var luhnRule = new LuhnValidationValidationRule<>(10);
        var specialCharRule = new PersonalNumberSpecialCharactersValidationValidationRule<>(6, 8, "[+\\-]");

        var personalNumberValidator = ValidatorFactory.createValidator(lengthRule, luhnRule, specialCharRule);

        String invalidPersonalNumberWithInvalidChecksum = "199001256557"; // Invalid checksum
        assertThrows(ValidationException.class, () -> personalNumberValidator.validate(invalidPersonalNumberWithInvalidChecksum));
    }

    @Test
    public void validateName_BlankInput() {
        var notBlankRule = new NonNullOrBlankValidationValidationRule<>();
        var specialCharNameRule = new AllowedCharactersValidationValidationRule<>(ValidationConstants.SwedishName.ALLOWED_CHARACTERS);

        var nameValidator = ValidatorFactory.createValidator(notBlankRule, specialCharNameRule);

        String blankName = "";
        assertThrows(ValidationException.class, () -> nameValidator.validate(blankName));
    }

    @Test
    public void validateName_NullInput() {
        var notBlankRule = new NonNullOrBlankValidationValidationRule<>();
        var specialCharNameRule = new AllowedCharactersValidationValidationRule<>(ValidationConstants.SwedishName.ALLOWED_CHARACTERS);

        var nameValidator = ValidatorFactory.createValidator(notBlankRule, specialCharNameRule);

        assertThrows(ValidationException.class, () -> nameValidator.validate(null));
    }

    @Test
    public void validateName_InvalidChars() {
        var notBlankRule = new NonNullOrBlankValidationValidationRule<>();
        var specialCharNameRule = new AllowedCharactersValidationValidationRule<>(ValidationConstants.SwedishName.ALLOWED_CHARACTERS);

        var nameValidator = ValidatorFactory.createValidator(notBlankRule, specialCharNameRule);

        String invalidName = "JohnDoe123"; // Invalid characters
        assertThrows(ValidationException.class, () -> nameValidator.validate(invalidName));
    }
}
