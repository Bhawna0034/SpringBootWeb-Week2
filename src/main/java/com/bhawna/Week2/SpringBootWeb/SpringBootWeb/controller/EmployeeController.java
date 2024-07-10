package com.bhawna.Week2.SpringBootWeb.SpringBootWeb.controller;

import com.bhawna.Week2.SpringBootWeb.SpringBootWeb.dto.EmployeeDTO;
import com.bhawna.Week2.SpringBootWeb.SpringBootWeb.entities.EmployeeEntity;
import com.bhawna.Week2.SpringBootWeb.SpringBootWeb.repositories.EmployeeRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path="/employees")
public class EmployeeController{
//    @GetMapping(path="/getSecretMessage")
//    public String getMySuperSecretMessage(){
//        return "Secret message: Learning Spring Boot";
//    }

  private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping(path="/{employeeId}")
      public EmployeeEntity getEmployeeId(@PathVariable(name = "employeeId") Long id){
      return employeeRepository.findById(id).orElse(null);
  }

  @GetMapping
    public List<EmployeeEntity> getAllEmployees(@RequestParam Integer age,
                                                @RequestParam String sortBy){
      return employeeRepository.findAll();
  }

  @PostMapping
    public EmployeeEntity createNewEmployee(@RequestBody EmployeeEntity inputEmployee){
      return employeeRepository.save(inputEmployee);
  }

  @PutMapping String updateEmployeeId(){
      return "Hello from PUT";
  }



}


