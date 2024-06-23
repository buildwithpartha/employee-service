package com.partha.springboot.service.impl;

import com.partha.springboot.apiclient.DepartmentApiClient;
import com.partha.springboot.dto.ApiResponseDto;
import com.partha.springboot.dto.DepartmentDto;
import com.partha.springboot.dto.EmployeeDto;
import com.partha.springboot.entity.Employee;
import com.partha.springboot.exception.ResourceNotFoundException;
import com.partha.springboot.mapper.EmployeeMapper;
import com.partha.springboot.repository.EmployeeRepository;
import com.partha.springboot.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    private ModelMapper modelMapper;

    //private RestTemplate restTemplate;

    //private WebClient webClient;

    private DepartmentApiClient departmentApiClient;

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
    public ApiResponseDto getEmployeeById(Long id){
        Employee employee = employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee doesn't exist with id: " + id));
        //return new EmployeeDto(employee.getId(), employee.getFirstName(), employee.getLastName(), employee.getEmail());
        //return modelMapper.map(employee, EmployeeDto.class);

        /*ResponseEntity<DepartmentDto> departmentDtoResponse = restTemplate.getForEntity("http://localhost:8080/api/departments/"+employee.getDepartmentCode(), DepartmentDto.class);
        DepartmentDto departmentDto = departmentDtoResponse.getBody();*/

        //DepartmentDto departmentDto = webClient.get().uri("http://localhost:8080/api/departments/"+employee.getDepartmentCode())
        //        .retrieve().bodyToMono(DepartmentDto.class).block();

        DepartmentDto departmentDto = departmentApiClient.getDepartmentByDepartmentCode(employee.getDepartmentCode());
        ApiResponseDto apiResponseDto = new ApiResponseDto(EmployeeMapper.MAPPER.mapToEmployeeDto(employee), departmentDto);
        return apiResponseDto;

    }
}
