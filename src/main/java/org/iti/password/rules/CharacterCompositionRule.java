package org.iti.password.rules;

import org.springframework.stereotype.Component;

/**
 * Valida a composição de caracteres da senha.
 *
 * Regras aplicadas:
 * - Deve conter pelo menos um dígito (0-9).
 * - Deve conter pelo menos uma letra minúscula.
 * - Deve conter pelo menos uma letra maiúscula.
 * - Deve conter pelo menos um caractere especial presente na constante SPECIAL.
 * - Não pode conter caracteres de espaço em branco.
 *
 * @param password a senha a ser validada
 * @return true se a senha obedecer todas as regras acima; false caso contrário
 */
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
