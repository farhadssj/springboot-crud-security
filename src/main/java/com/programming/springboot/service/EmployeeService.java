package com.programming.springboot.service;

import com.programming.springboot.exception.EmployeeInfoException;
import com.programming.springboot.model.EmployeeInfo;
import com.programming.springboot.repositiry.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Service("EmployeeService")
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public void saveEmployeeInfo(String name, int age, String department, int salary){
        EmployeeInfo employeeInfo = new EmployeeInfo();
        employeeInfo.setName(name);
        employeeInfo.setAge(age);
        employeeInfo.setDepartment(department);
        employeeInfo.setSalary(salary);
        employeeRepository.save(employeeInfo);
    }

    public Page<EmployeeInfo> getAllEmployeeInfo(int page, int size) {
        Sort sorting = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page, size, sorting);
        return employeeRepository.findAll(pageable);
    }

    public EmployeeInfo getEmployeeInfo(Long id) throws EmployeeInfoException {
        return employeeRepository.findById(id).orElseThrow(
                () -> new EmployeeInfoException(HttpStatus.NOT_FOUND.value(), "NO_USER_FOUND", "No user found exception"));
    }

    public void deleteEmployeeInfo(Long id) throws EmployeeInfoException {
        EmployeeInfo employeeInfo = this.getEmployeeInfo(id);
        employeeRepository.delete(employeeInfo);
    }

    public void updateEmployeeInfo(Long id, String name, int age, String department, int salary){
        EmployeeInfo employeeInfo = new EmployeeInfo();
        employeeInfo.setId(id);
        employeeInfo.setName(name);
        employeeInfo.setAge(age);
        employeeInfo.setDepartment(department);
        employeeInfo.setSalary(salary);
        employeeRepository.save(employeeInfo);
    }

    public void patchEmployeeInfo(Long id, Map<String, Object> updatedData) throws EmployeeInfoException {
        EmployeeInfo employeeInfo = this.getEmployeeInfo(id);
        updatedData.forEach((key, value) -> {
            if (key.equals("name")){
                employeeInfo.setName((String) value);
            }
            if (key.equals("age")){
                employeeInfo.setAge((int) value);
            }
            if (key.equals("department")){
                employeeInfo.setDepartment((String) value);
            }
            if (key.equals("salary")){
                employeeInfo.setSalary((int) value);
            }
        });
        employeeRepository.save(employeeInfo);
    }

}
