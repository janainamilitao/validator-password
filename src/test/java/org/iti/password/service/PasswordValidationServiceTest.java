package org.iti.password.service;

import org.iti.password.dto.PasswordResponse;
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
        PasswordResponse res = service.validate("AbTp9!fok");
        assertTrue(res.valid());
        assertTrue(res.failures().isEmpty());
    }

    @Test
    void sizeLessThanNine_shouldReturnFalse() {
        var service = serviceWithAllRules();
        PasswordResponse res = service.validate("AbTp9!fo"); // 8 chars
        assertFalse(res.valid());
        assertTrue(res.failures().contains("Senha deve conter pelo menos 9 caracteres."));
    }

    @Test
    void semDigit_shouldReturnFalse() {
        var service = serviceWithAllRules();
        PasswordResponse res = service.validate("AbTp!fokK");
        assertFalse(res.valid());
        assertTrue(res.failures().contains("Senha deve conter ao menos 1 dígito, 1 minúscula, 1 maiúscula, 1 caractere especial e não pode ter espaços."));
    }

    @Test
    void noLowerCase_shouldReturnFalse() {
        var service = serviceWithAllRules();
        PasswordResponse res = service.validate("ABTP9!FOK");
        assertFalse(res.valid());
        assertTrue(res.failures().contains("Senha deve conter ao menos 1 dígito, 1 minúscula, 1 maiúscula, 1 caractere especial e não pode ter espaços."));
    }

    @Test
    void noUpperCase_shouldReturnFalse() {
        var service = serviceWithAllRules();
        PasswordResponse res = service.validate("abtp9!fok");
        assertFalse(res.valid());
        assertTrue(res.failures().contains("Senha deve conter ao menos 1 dígito, 1 minúscula, 1 maiúscula, 1 caractere especial e não pode ter espaços."));
    }

    @Test
    void noSpecialCharacter_shouldReturnFalse() {
        var service = serviceWithAllRules();
        PasswordResponse res = service.validate("AbTp9afok");
        assertFalse(res.valid());
        assertTrue(res.failures().contains("Senha deve conter ao menos 1 dígito, 1 minúscula, 1 maiúscula, 1 caractere especial e não pode ter espaços."));
    }

    @Test
    void  RepeatedCharacter_shouldReturnFalse() {
        var service = serviceWithAllRules();
        PasswordResponse res = service.validate("AbTp9!fokk");
        assertFalse(res.valid());
        assertTrue(res.failures().contains("Senha não pode conter caracteres repetidos (case-insensitive)."));
    }

    @Test
    void WhiteSpace_shouldReturnFalse() {
        var service = serviceWithAllRules();
        PasswordResponse res = service.validate("AbTp9! foK");
        assertFalse(res.valid());
        assertTrue(res.failures().contains("Senha deve conter ao menos 1 dígito, 1 minúscula, 1 maiúscula, 1 caractere especial e não pode ter espaços."));
    }
}
