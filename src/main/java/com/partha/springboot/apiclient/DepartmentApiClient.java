package com.partha.springboot.apiclient;

import com.partha.springboot.dto.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(url = "http://localhost:8080", value = "DEPARTMENT-SERVICE")

//Below annotation is for service registry with load balancer using app name
@FeignClient(name = "department-service")
public interface DepartmentApiClient {

    @GetMapping("/api/departments/{department-code}")
    DepartmentDto getDepartmentByDepartmentCode(@PathVariable("department-code") String departmentCode);

}
