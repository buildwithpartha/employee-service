package com.partha.springboot.service.impl;

import com.partha.springboot.dto.EmployeeDto;
import com.partha.springboot.entity.Employee;
import com.partha.springboot.mapper.EmployeeMapper;
import com.partha.springboot.repository.EmployeeRepository;
import com.partha.springboot.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    private ModelMapper modelMapper;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto){
        //Employee savedEmployee = employeeRepository.save(new Employee(employeeDto.getId(),employeeDto.getFirstName(),
        //        employeeDto.getLastName(), employeeDto.getEmail()));
        //Employee savedEmployee = employeeRepository.save(modelMapper.map(employeeDto, Employee.class));
        Employee savedEmployee = employeeRepository.save(EmployeeMapper.MAPPER.mapToEmployee(employeeDto));


        //return new EmployeeDto(savedEmployee.getId(), savedEmployee.getFirstName(), savedEmployee.getLastName(), savedEmployee.getEmail());
        //return modelMapper.map(savedEmployee, EmployeeDto.class);
        return EmployeeMapper.MAPPER.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long id){
        Employee employee = employeeRepository.findById(id).get();
        //return new EmployeeDto(employee.getId(), employee.getFirstName(), employee.getLastName(), employee.getEmail());
        //return modelMapper.map(employee, EmployeeDto.class);
        return EmployeeMapper.MAPPER.mapToEmployeeDto(employee);

    }
}
