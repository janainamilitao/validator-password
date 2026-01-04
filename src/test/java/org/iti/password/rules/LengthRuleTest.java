package org.iti.password.rules;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LengthRuleTest {

    private final LengthRule rule = new LengthRule();

    @Test
    void passwordWithNineOrMoreCharacters_shouldReturnTrue() {
        assertTrue(rule.isValid("Abc1!Defg")); // 9 chars
        assertTrue(rule.isValid("123456789"));
    }

    @Test
    void passwordWithLessThanNineCharacters_shouldReturnFalse() {
        assertFalse(rule.isValid("Abc1!Def")); // 8 chars
        assertFalse(rule.isValid("short"));
    }

    @Test
    void nullPassword_shouldReturnFalse() {
        assertFalse(rule.isValid(null));
    }
}

