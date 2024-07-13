package com.bhawna.Week2.SpringBootWeb.SpringBootWeb.controller;

import com.bhawna.Week2.SpringBootWeb.SpringBootWeb.dto.EmployeeDTO;
import com.bhawna.Week2.SpringBootWeb.SpringBootWeb.exceptions.ResourceNotFoundException;
import com.bhawna.Week2.SpringBootWeb.SpringBootWeb.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
      public ResponseEntity<EmployeeDTO> getEmployeeId(@PathVariable(name = "employeeId") Long id){
      Optional<EmployeeDTO> employeeDTO = employeeService.getEmployeeById(id);
      return employeeDTO
              .map(employeeDTO1 -> ResponseEntity.ok(employeeDTO1))
              .orElseThrow(() -> new ResourceNotFoundException("Resource Not Found with id: " +id));

    }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(@RequestParam(required = false) Integer age,
                                             @RequestParam(required = false) String sortBy){
      return ResponseEntity.ok(employeeService.getAllEmployees());
  }

  @PostMapping
    public ResponseEntity<EmployeeDTO> createNewEmployee(@RequestBody @Valid EmployeeDTO inputEmployee){
      EmployeeDTO savedEmployee = employeeService.createNewEmployee(inputEmployee);
      return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
  }

  @PutMapping(path = "/{employeeId}")
  public ResponseEntity<EmployeeDTO> updateEmployeeById(@RequestBody @Valid EmployeeDTO employeeDTO, @PathVariable Long employeeId){
      return ResponseEntity.ok(employeeService.updateEmployeeId(employeeId, employeeDTO));
  }

  @DeleteMapping(path = "/{employeeId}")
  public ResponseEntity<Boolean> deleteEmployeeById(@PathVariable @Valid Long employeeId){
   boolean gotDeleted = employeeService.deleteEmployeeById(employeeId);
   if(gotDeleted)
     return ResponseEntity.ok(true);
   return ResponseEntity.notFound().build();
  }

  @PatchMapping(path = "/{employeeId}")
  public ResponseEntity<EmployeeDTO> updatePartiallyEmployeeById(@RequestBody @Valid Map<String,Object> updates,
                                                 @PathVariable Long employeeId) {
    EmployeeDTO employeeDTO = employeeService.updatePartiallyEmployeeId(employeeId, updates);
    if(employeeDTO == null)
      return ResponseEntity.notFound().build();
    return ResponseEntity.ok(employeeDTO);
  }



}


