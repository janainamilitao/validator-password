package org.iti.password.rules;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class NoRepeatedCharacterRule implements PasswordRule {
    public boolean isValid(String password) {
        Set<Character> seen = new HashSet<>();
        for (char c : password.toCharArray()) {
            if (!seen.add(c)) return false;
        }
        return true;
    }
}
