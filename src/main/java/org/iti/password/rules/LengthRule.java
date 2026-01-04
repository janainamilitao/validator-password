package org.iti.password.rules;

import org.springframework.stereotype.Component;

@Component
public class LengthRule implements PasswordRule {
    public boolean isValid(String password) {
        return password != null && password.length() >= 9;
    }
}
