package org.iti.password.rules;

import org.springframework.stereotype.Component;

@Component
public class CharacterCompositionRule implements PasswordRule {
    private static final String SPECIAL = "!@#$%^&*()-+";


    public boolean isValid(String password) {
        boolean digit = false, lower = false, upper = false, special = false;


        for (char c : password.toCharArray()) {
            if (Character.isWhitespace(c)) return false;
            if (Character.isDigit(c)) digit = true;
            else if (Character.isLowerCase(c)) lower = true;
            else if (Character.isUpperCase(c)) upper = true;
            else if (SPECIAL.indexOf(c) >= 0) special = true;
        }


        return digit && lower && upper && special;
    }
}
