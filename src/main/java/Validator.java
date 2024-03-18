package main.java;

import main.java.exceptions.ValidationException;

public interface Validator<T> {
    boolean validate(T input) throws ValidationException;
}
