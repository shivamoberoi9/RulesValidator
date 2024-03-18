package main.java.rules;

public interface ValidationRule<T> {
    boolean apply(T input);
    String getFailureReason();
}
