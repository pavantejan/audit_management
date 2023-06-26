package com.cognizant.Severity.service;

import com.cognizant.Severity.feign.AuthFeign;
import com.cognizant.Severity.model.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationServiceImpl implements AuthorizationService{

    @Autowired
    public AuthFeign authFeign;

    public AuthResponse authResponse;

    @Override
    public boolean validateJwt(String jwt) {
        ResponseEntity<AuthResponse> responseEntity= authFeign.validate(jwt);

        authResponse = responseEntity.getBody();

        return responseEntity.getBody().isValid();
    }

    public int getEmpId(){

        return authResponse.getEmpId();
    }

}
