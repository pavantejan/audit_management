package com.cognizant.Severity.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    private int empId;
    private String name;
    private String projectName;
    private boolean isValid;
}
