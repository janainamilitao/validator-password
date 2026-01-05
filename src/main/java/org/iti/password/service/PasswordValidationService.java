package org.iti.password.service;

import org.iti.password.dto.PasswordResponse;
import org.iti.password.rules.PasswordRule;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PasswordValidationService {


    private final List<PasswordRule> rules;


    public PasswordValidationService(List<PasswordRule> rules) {
        this.rules = rules;
    }


    public PasswordResponse validate(String password) {
        List<String> failures = rules.stream()
                .filter(rule -> !rule.isValid(password))
                .map(PasswordRule::errorMessage)
                .collect(Collectors.toList());
        boolean valid = failures.isEmpty();
        return new PasswordResponse(valid, failures);
    }
}
