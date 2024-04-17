package com.programming.springboot.repositiry;

import com.programming.springboot.model.EmployeeInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("EmployeeRepository")
public interface EmployeeRepository extends JpaRepository<EmployeeInfo, Long> {

}
