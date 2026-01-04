package org.iti.password.rules;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Valida se uma senha não contém caracteres repetidos.
 *
 * Regras aplicadas:
 * - Retorna false se a senha for null.
 * - Considera caracteres repetidos de forma case-insensitive: 'a' e 'A' são tratados como iguais.
 * - Compare caracteres individuais
 *
 * @param password a senha a ser validada
 * @return true se não houver caracteres repetidos (ignorando diferença entre maiúsculas/minúsculas); false caso contrário
 */

@Component
public class NoRepeatedCharacterRule implements PasswordRule {
    public boolean isValid(String password) {
        if (password == null) return false;

        Set<Character> seen = new HashSet<>();
        for (char c : password.toCharArray()) {
            // Normalize para letra minúscula para tornar a verificação case-insensitive
            char normalized = Character.toLowerCase(c);
            if (!seen.add(normalized)) return false;
        }
        return true;
    }
}
