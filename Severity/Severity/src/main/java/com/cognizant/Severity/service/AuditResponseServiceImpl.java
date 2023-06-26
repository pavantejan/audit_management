package com.cognizant.Severity.service;

import com.cognizant.Severity.model.AuditBenchmark;
import com.cognizant.Severity.model.AuditQuestion;
import com.cognizant.Severity.model.AuditRequest;
import com.cognizant.Severity.model.AuditResponse;
import com.cognizant.Severity.repository.SeverityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AuditResponseServiceImpl implements AuditResponseService{

//    Acceptable no - Benchmark no's for a audit type
//    actual no - questions response no's for a audit type


    @Autowired
    public SeverityRepository severityRepo;
    @Autowired
    public AuthorizationService authService;


    public AuditResponse createAuditResponse(int acceptableNo,int actualNo,String auditType,int auditId){

        AuditResponse auditResponse = new AuditResponse();
        auditResponse.setAudit_id(auditId);
        auditResponse.setAudit_type(auditType);





        if(auditType.equalsIgnoreCase("Internal") && actualNo <= acceptableNo){
            auditResponse.setProject_execution_status("GREEN");
            auditResponse.setRemedial_action_duration("No action needed");

        } else if (auditType.equalsIgnoreCase("Internal") && actualNo > acceptableNo) {
            auditResponse.setProject_execution_status("RED");
            auditResponse.setRemedial_action_duration("Action to be taken in 2 weeks");

        } else if (auditType.equalsIgnoreCase("SOX") && actualNo <= acceptableNo) {
            auditResponse.setProject_execution_status("GREEN");
            auditResponse.setRemedial_action_duration("No action needed");

        }else {
            auditResponse.setProject_execution_status("RED");
            auditResponse.setRemedial_action_duration("Action to be taken in 1 week");
        }
//        System.out.println(auditResponse.getAuditId());

        return auditResponse;
    }

    public int countNo(List<AuditQuestion> auditQuestions){
        int count=0;
        for(AuditQuestion aq: auditQuestions){
            if ( aq.getResponse().equals("NO") ){
                count+=1;
            }
        }
        return count;
    }

    @Override
    public AuditResponse getAuditResponse(List<AuditBenchmark> auditBenchmark, String auditType, List<AuditQuestion> auditQuestions) {

        int acceptableNo=0;
        int auditId = 0;
        for(AuditBenchmark ab : auditBenchmark){
            if (ab.getAudit_type().equals(auditType) ){
                acceptableNo = ab.getBenchmark_no_answers();
                auditId = ab.getBenchmark_id();
            }
        }

        return createAuditResponse(acceptableNo,countNo(auditQuestions),auditType,auditId);
    }

    @Override
    public AuditResponse saveAuditResponse(AuditResponse auditResponse, AuditRequest auditRequest) {
//        int id = (int)(Math.random() * (50 - 1 + 1) + 1);
//        auditResponse.setAuditId(id);

        Date dNow = new Date( );
        SimpleDateFormat ft =
                new SimpleDateFormat ("dd.MM.yyyy 'at' hh:mm");

        System.out.println("Current Date: " + ft.format(dNow));

//        List<AuditResponse> responses;

        auditResponse.setEmp_id(authService.getEmpId());
        auditResponse.setProject_name(auditRequest.getProjectName());
        auditResponse.setProject_manager_name(auditRequest.getProjectManagerName());
        auditResponse.setCreation_date_and_time(ft.format(dNow));

//        responses = severityRepo.findAll();
//        if ( !responses.isEmpty() ){
//           for(AuditResponse res:responses){
//               if ( res.getAuditType() == auditResponse.getAuditType() ){
//                   severityRepo.deleteById(auditResponse.getId());
//               }
//           }
//        }

//        List<AuditResponse> all = severityRepo.findAll();

//        System.out.println("Inside the findall function using repo1");
//        System.out.println(all);

//        severityRepo.deleteResponse(authService.getEmpId(),auditResponse.getAudit_type());

//        all = severityRepo.findAll();

//        System.out.println("Inside the findall function using repo2");
//        System.out.println(all);

        List<AuditResponse> allAuditResponse = severityRepo.findAll();
        if ( !allAuditResponse.isEmpty() ){
           for(AuditResponse res:allAuditResponse){
               if ( res.getAudit_type().equals(auditResponse.getAudit_type()) && res.getEmp_id() == auditResponse.getEmp_id() ){
                   System.out.println(res.getId());
                   severityRepo.deleteById(res.getId());
               }
           }
        }

//        severityRepo.delete();
        severityRepo.save(auditResponse);


        return auditResponse;
    }

    @Override
    public List<AuditResponse> getResponses(int empid) {
        List<AuditResponse> responses = severityRepo.findAll();
        List<AuditResponse> responsesOut = new ArrayList<>();
//        List<AuditResponse> responses = severityRepo.findAllById(empid);
//        System.out.println("Hi inside get response");
        for(AuditResponse ar:responses){
            if(ar.getEmp_id()==empid){
                responsesOut.add(ar);
            }
            System.out.println(ar.getEmp_id());
        }
        return responsesOut;
    }
}
