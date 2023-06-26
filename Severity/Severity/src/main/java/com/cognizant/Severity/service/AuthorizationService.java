package com.cognizant.Severity.service;

public interface AuthorizationService {
    public boolean validateJwt(String jwt);

    public int getEmpId();
}
