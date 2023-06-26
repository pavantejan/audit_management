package com.cognizant.Severity.service;

import com.cognizant.Severity.feign.BenchmarkFeign;
import com.cognizant.Severity.model.*;
import com.cognizant.Severity.repository.SeverityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuditRequestResponseServiceImpl implements AuditRequestResponseService{

    @Autowired
    private AuditResponseService auditResponseService;

    @Autowired
    private SeverityRepository severityRepository;

    @Autowired
    private BenchmarkFeign benchmarkFeign;

    @Autowired
    private AuthorizationService authService;

    @Override
    public AuditResponse getAuditRequestResponse(AuditRequest auditRequest, String jwt) {

        String auditType;
//        List<AuditQuestionsResponse> auditQuestionsResponses = new ArrayList<AuditQuestionsResponse>();
//        auditQuestionsResponse.setEmp_id(authService.getEmpId());
//        AuditQuestionsResponse aqr = new AuditQuestionsResponse();


        List<AuditQuestion> auditQuestionList = auditRequest.getAuditDetail().getAuditQuestions();
//
//        System.out.println(auditQuestionList);
//        for(AuditQuestion auditQuestion : auditQuestionList){
//            aqr.setEmp_id(authService.getEmpId());
//            aqr.setAudit_type(auditQuestion.getAudit_type());
//            aqr.setQuestion(auditQuestion.getQuestions());
//            aqr.setResponse(auditQuestion.getResponse());
//            System.out.println(aqr);
//
//            auditQuestionsResponses.add(aqr);
//            System.out.println(auditQuestionsResponses);
////            severityRepository.save(auditQuestionsResponses);
//        }
//        severityRepository.save(auditQuestionsResponses);







        System.out.println(jwt);
        List<AuditBenchmark> auditBenchmarkList = benchmarkFeign.getAuditBenchmark(jwt);
        System.out.println("in audit request response 1");
        auditType = auditRequest.getAuditDetail().getAuditType();
        System.out.println("in audit request response 2");

        System.out.println("in audit request response 3");

        AuditResponse auditResponse = auditResponseService.getAuditResponse(auditBenchmarkList,auditType,auditQuestionList);

        return auditResponseService.saveAuditResponse(auditResponse,auditRequest);
    }
}
