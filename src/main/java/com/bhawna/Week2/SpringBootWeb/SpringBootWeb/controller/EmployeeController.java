package com.bhawna.Week2.SpringBootWeb.SpringBootWeb.controller;

import com.bhawna.Week2.SpringBootWeb.SpringBootWeb.dto.EmployeeDTO;
import com.bhawna.Week2.SpringBootWeb.SpringBootWeb.entities.EmployeeEntity;
import com.bhawna.Week2.SpringBootWeb.SpringBootWeb.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/employees")
public class EmployeeController{
//    @GetMapping(path="/getSecretMessage")
//    public String getMySuperSecretMessage(){
//        return "Secret message: Learning Spring Boot";
//    }

  private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping(path="/{employeeId}")
      public EmployeeDTO getEmployeeId(@PathVariable(name = "employeeId") Long id){
      return employeeService.getEmployeeById(id);
  }

  @GetMapping
    public List<EmployeeDTO> getAllEmployees(@RequestParam(required = false) Integer age,
                                             @RequestParam(required = false) String sortBy){
      return employeeService.getAllEmployees();
  }

  @PostMapping
    public EmployeeDTO createNewEmployee(@RequestBody EmployeeDTO inputEmployee){
      return employeeService.createNewEmployee(inputEmployee);
  }

  @PutMapping String updateEmployeeId(){
      return "Hello from PUT";
  }



}


