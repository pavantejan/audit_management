package com.cognizant.Checklist.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {

    private int empId;
    private String name;
    private String projectName;
    private boolean isValid;


}
