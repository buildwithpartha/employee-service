package com.partha.springboot.service;

import com.partha.springboot.dto.ApiResponseDto;
import com.partha.springboot.dto.EmployeeDto;

public interface EmployeeService {

    EmployeeDto createEmployee(EmployeeDto employeeDto);

    ApiResponseDto getEmployeeById(Long id);
}
