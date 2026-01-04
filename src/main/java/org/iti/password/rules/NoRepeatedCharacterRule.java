package org.iti.password.rules;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
/**
 * Valida a composição  da senha.
 *
 * Regras aplicadas:
 * - Deve espaços em brancos.
 *
 * @param password a senha a ser validada
 * @return true se a senha obedecer todas as regras acima; false caso contrário
 */


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
