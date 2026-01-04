package org.iti.password.rules;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CharacterCompositionRuleTest {

    private final CharacterCompositionRule rule = new CharacterCompositionRule();

    @Test
    void validPassword_shouldReturnTrue() {
        assertTrue(rule.isValid("Abc1!Defg"));
    }

    @Test
    void missingDigit_shouldReturnFalse() {
        assertFalse(rule.isValid("Abc!DefGh"));
    }

    @Test
    void missingLower_shouldReturnFalse() {
        assertFalse(rule.isValid("ABC1!DEFG"));
    }

    @Test
    void missingUpper_shouldReturnFalse() {
        assertFalse(rule.isValid("abc1!defg"));
    }

    @Test
    void missingSpecial_shouldReturnFalse() {
        assertFalse(rule.isValid("Abc1DefGh"));
    }

    @Test
    void containsWhitespace_shouldReturnFalse() {
        assertFalse(rule.isValid("Abc1!De fg"));
    }

    @Test
    void unknownSpecial_shouldReturnFalse() {
        assertFalse(rule.isValid("Abc1?Defg"));
    }
}

