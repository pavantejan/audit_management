package com.cognizant.Severity.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity
//@Table(name = "audit_questions_response")
public class AuditQuestionsResponse {

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int emp_id;
    private String question;
    private String audit_type;
    private String response;

}
