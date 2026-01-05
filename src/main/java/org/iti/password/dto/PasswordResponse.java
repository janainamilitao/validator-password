package org.iti.password.dto;

import java.util.List;

public record PasswordResponse(boolean valid, List<String> failures) {
}
