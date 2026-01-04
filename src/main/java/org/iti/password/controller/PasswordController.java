package org.iti.password.controller;

import org.iti.password.dto.PasswordRequest;
import org.iti.password.dto.PasswordResponse;
import org.iti.password.service.PasswordValidationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/password")
public class PasswordController {


    private final PasswordValidationService service;


    public PasswordController(PasswordValidationService service) {
        this.service = service;
    }


    @PostMapping("/validate")
    public PasswordResponse validate(@RequestBody PasswordRequest request) {
        boolean valid = service.validate(request.password());
        return new PasswordResponse(valid);
    }
}