package org.iti.password.rules;

public interface PasswordRule {

    boolean isValid(String password);

    default String errorCode() {
        return this.getClass().getSimpleName();
    }

    default String errorMessage() {
        return "Validation failed: " + errorCode();
    }
}
