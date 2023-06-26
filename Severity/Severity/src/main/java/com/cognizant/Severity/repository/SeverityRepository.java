package com.cognizant.Severity.repository;

import com.cognizant.Severity.model.AuditResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeverityRepository extends JpaRepository<AuditResponse, Integer> {
//    @Query(value = "select q from audit_response q where q.emp_id=?1")
//    List<AuditResponse> findAllById(int empid);

//    @Modifying
//    @Query(value = "delete from audit_response q where q.emp_id=?1, q.audit_type=?2")
//    void deleteResponse(int empid, String type);

//    @Query(value = "select q from audit_response")
//    List<AuditResponse> getResponses();
}
