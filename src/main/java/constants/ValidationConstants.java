package main.java.constants;

public class ValidationConstants {

    public static class SwedishPersonalNumber {
        public static final int MIN_LENGTH = 10;
        public static final int MAX_LENGTH = 12;
        public static final int LUHN_DIGITS_LENGTH = 10;
        public static final int SPECIAL_CHARACTER_AFTER_MIN_LENGTH = 6;
        public static final int SPECIAL_CHARACTER_AFTER_MAX_LENGTH = 8;
        public static final String SPECIAL_CHARACTERS = "[+\\-]";

    }

    public static class SwedishName {
        public static final String ALLOWED_CHARACTERS = "[a-zA-ZåäöÅÄÖ ]+";
    }
}

