package org.iti.password.rules;

import org.springframework.stereotype.Component;
/**
 * Valida a composição  do tamanho da senha.
 *
 * Regras aplicadas:
 * - Deve conter pelo menos 9 dígitos.
 *
 * @param password a senha a ser validada
 * @return true se a senha obedecer todas as regras acima; false caso contrário
 */

@Component
public class LengthRule implements PasswordRule {
    public boolean isValid(String password) {
        return password != null && password.length() >= 9;
    }

    @Override
    public String errorMessage() {
        return "Senha deve conter pelo menos 9 caracteres.";
    }
}
