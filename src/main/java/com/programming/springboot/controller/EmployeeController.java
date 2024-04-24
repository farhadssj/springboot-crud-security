package com.programming.springboot.controller;

import com.programming.springboot.exception.EmployeeInfoException;
import com.programming.springboot.model.EmployeeInfo;
import com.programming.springboot.model.RestApiResponse;
import com.programming.springboot.model.request.InputEmployeeInfo;
import com.programming.springboot.service.EmployeeService;
import com.programming.springboot.util.Constant;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping(path = Constant.ADD_EMPLOYEE_INFO_PATH, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> saveEmployeeInfo(@RequestBody @Valid InputEmployeeInfo inputEmployeeInfo) {
        employeeService.saveEmployeeInfo(inputEmployeeInfo.getName(), inputEmployeeInfo.getAge(), inputEmployeeInfo.getDepartment(), inputEmployeeInfo.getSalary());
        return RestApiResponse.buildResponseWithoutDetails(HttpStatus.CREATED.value(), "Successfully saved");
    }

    @GetMapping(path = Constant.GET_ALL_EMPLOYEE_INFO_PATH, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> getAllEmployeeInfo() {
        List<EmployeeInfo> employeeInfoList = employeeService.getAllEmployeeInfo();
        return RestApiResponse.buildResponseWithDetails(HttpStatus.OK.value(), "Successfully fetch All Employees", employeeInfoList);
    }

    @GetMapping(path = Constant.GET_SPECIFIC_EMPLOYEE_INFO_PATH + "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> getSpecificEmployeeInfo(@PathVariable @Valid Long id) throws EmployeeInfoException {
        EmployeeInfo employeeInfo = employeeService.getEmployeeInfo(id);
        return RestApiResponse.buildResponseWithDetails(HttpStatus.OK.value(), "Successfully fetch Employee", employeeInfo);
    }

    @DeleteMapping(path = Constant.DELETE_EMPLOYEE_INFO_PATH + "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> deleteEmployeeInfo(@PathVariable @Valid Long id) throws EmployeeInfoException {
        employeeService.deleteEmployeeInfo(id);
        return RestApiResponse.buildResponseWithoutDetails(HttpStatus.OK.value(), "Successfully Delete Employee");
    }

    @PutMapping(path = Constant.UPDATE_EMPLOYEE_INFO_PATH + "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> updateEmployeeInfo(@PathVariable @Valid Long id, @RequestBody @Valid InputEmployeeInfo inputEmployeeInfo) throws EmployeeInfoException {
        EmployeeInfo employeeInfo = employeeService.getEmployeeInfo(id);
        employeeService.updateEmployeeInfo(id, inputEmployeeInfo.getName(), inputEmployeeInfo.getAge(), inputEmployeeInfo.getDepartment(), inputEmployeeInfo.getSalary());
        return RestApiResponse.buildResponseWithoutDetails(HttpStatus.OK.value(), "Successfully Update Employee");
    }

    @PatchMapping(path = Constant.PATCH_EMPLOYEE_INFO_PATH + "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> patchEmployeeInfo(@PathVariable @Valid Long id, @RequestBody @Valid Map<String, Object> updatedData) throws EmployeeInfoException {
        employeeService.patchEmployeeInfo(id, updatedData);
        return RestApiResponse.buildResponseWithoutDetails(HttpStatus.OK.value(), "Successfully Patch Update Employee");
    }

}
