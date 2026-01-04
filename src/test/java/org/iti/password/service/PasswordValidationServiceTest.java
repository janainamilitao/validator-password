package org.iti.password.service;

import org.iti.password.rules.CharacterCompositionRule;
import org.iti.password.rules.LengthRule;
import org.iti.password.rules.NoRepeatedCharacterRule;
import org.iti.password.rules.PasswordRule;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PasswordValidationServiceTest {

    private PasswordValidationService serviceWithAllRules() {
        List<PasswordRule> rules = List.of(
                new LengthRule(),
                new CharacterCompositionRule(),
                new NoRepeatedCharacterRule()
        );
        return new PasswordValidationService(rules);
    }

    @Test
    void validPassword_shouldReturnTrue() {
        var service = serviceWithAllRules();
        assertTrue(service.validate("AbTp9!fok"));
    }

    @Test
    void sizeLessThanNine_shouldReturnFalse() {
        var service = serviceWithAllRules();
        assertFalse(service.validate("AbTp9!fo")); // 8 chars
    }

    @Test
    void semDigit_shouldReturnFalse() {
        var service = serviceWithAllRules();
        assertFalse(service.validate("AbTp!fokK"));
    }

    @Test
    void noLowerCase_shouldReturnFalse() {
        var service = serviceWithAllRules();
        assertFalse(service.validate("ABTP9!FOK"));
    }

    @Test
    void noUpperCase_shouldReturnFalse() {
        var service = serviceWithAllRules();
        assertFalse(service.validate("abtp9!fok"));
    }

    @Test
    void noSpecialCharacter_shouldReturnFalse() {
        var service = serviceWithAllRules();
        assertFalse(service.validate("AbTp9afok"));
    }

    @Test
    void  RepeatedCharacter_shouldReturnFalse() {
        var service = serviceWithAllRules();
        assertFalse(service.validate("AbTp9!fokk"));
    }

    @Test
    void WhiteSpace_shouldReturnFalse() {
        var service = serviceWithAllRules();
        assertFalse(service.validate("AbTp9! foK"));
    }
}

