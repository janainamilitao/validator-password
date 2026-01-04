package org.iti.password.service;

import org.iti.password.rules.PasswordRule;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PasswordValidationService {


    private final List<PasswordRule> rules;


    public PasswordValidationService(List<PasswordRule> rules) {
        this.rules = rules;
    }


    public boolean validate(String password) {
        return rules.stream().allMatch(rule -> rule.isValid(password));
    }
}
