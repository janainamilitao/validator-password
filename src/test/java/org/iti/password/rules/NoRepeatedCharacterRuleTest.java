package org.iti.password.rules;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NoRepeatedCharacterRuleTest {

    private final NoRepeatedCharacterRule rule = new NoRepeatedCharacterRule();

    @Test
    void validPassword_shouldReturnTrue() {
        assertTrue(rule.isValid("AbTp9!fok"));
    }

    @Test
    void repeatedSameCharacter_shouldReturnFalse() {
        assertFalse(rule.isValid("AbTp9!fokk")); // 'k' repeated
    }

    @Test
    void repeatedDifferentCase_shouldReturnFalse() {
        assertFalse(rule.isValid("AbTp9!foO")); // 'o' and 'O' repeated
    }

    @Test
    void nullPassword_shouldReturnFalse() {
        assertFalse(rule.isValid(null));
    }

    @Test
    void emptyPassword_shouldReturnTrue() {
        assertTrue(rule.isValid(""));
    }
}

