package com.partha.springboot.mapper;

import com.partha.springboot.dto.EmployeeDto;
import com.partha.springboot.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmployeeMapper {

    public static EmployeeMapper MAPPER = Mappers.getMapper(EmployeeMapper.class);

    EmployeeDto mapToEmployeeDto(Employee employee);

    Employee mapToEmployee(EmployeeDto employeeDto);

}
