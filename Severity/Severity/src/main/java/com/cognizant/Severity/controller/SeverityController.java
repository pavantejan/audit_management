package com.cognizant.Severity.controller;

import com.cognizant.Severity.model.AuditQuestion;
import com.cognizant.Severity.model.AuditRequest;
import com.cognizant.Severity.model.AuditResponse;
import com.cognizant.Severity.service.AuditRequestResponseService;
import com.cognizant.Severity.service.AuditResponseService;
import com.cognizant.Severity.service.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class SeverityController {

    @Autowired
    private AuthorizationService authorizationService;

    @Autowired
    private AuditRequestResponseService auditRequestResponseService;

    @Autowired
    private AuditResponseService auditResponseService;

    @GetMapping(value = "/severity")
    public String welcomeCheck(){
        return "Welcome, This is severity request";
    }

    @GetMapping(value = "/getAuditResponses")
    public List<AuditResponse> getAuditResponse(@RequestHeader("Authorization") String jwt) throws Exception {

        List<AuditResponse> responses = new ArrayList<>();

        try{
            if( !StringUtils.isEmpty(jwt) &&  authorizationService.validateJwt(jwt)){

               responses = auditResponseService.getResponses(authorizationService.getEmpId());
            }
        }catch (Exception e){
            System.out.println(e+ " the exception is from get audit responses");
            throw new Exception("The JWT token is not valid");
        }

        return responses;
    }

    @PostMapping(value = "/ProjectExecutionStatus")
    public AuditResponse getProjectExecutionStatus(@RequestHeader("Authorization") String jwt,@RequestBody AuditRequest auditRequest) throws Exception {

        System.out.println("Heloooo");
        AuditResponse auditResponse = new AuditResponse();
        System.out.println(auditRequest);
        try{
            if( !StringUtils.isEmpty(jwt) &&  authorizationService.validateJwt(jwt)){
                System.out.println("in controller");
                auditResponse = auditRequestResponseService.getAuditRequestResponse(auditRequest,jwt);
                System.out.println("in controller 1");
            }
        }catch (Exception e){
            System.out.println(e+ " the exception is from project execution status");
            throw new Exception("The JWT token is not valid");
        }


//        System.out.println(auditResponseService.getResponses(authorizationService.getEmpId()));

        return auditResponse;
    }
}
