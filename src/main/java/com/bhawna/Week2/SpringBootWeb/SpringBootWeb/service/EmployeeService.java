package com.bhawna.Week2.SpringBootWeb.SpringBootWeb.service;

import com.bhawna.Week2.SpringBootWeb.SpringBootWeb.dto.EmployeeDTO;
import com.bhawna.Week2.SpringBootWeb.SpringBootWeb.entities.EmployeeEntity;
import com.bhawna.Week2.SpringBootWeb.SpringBootWeb.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public Optional<EmployeeDTO> getEmployeeById(Long id) {
//        Optional <EmployeeEntity> employeeEntity = employeeRepository.findById(id);
//        return employeeEntity.map(employeeEntity1 -> modelMapper.map(employeeEntity1, EmployeeDTO.class));
      return employeeRepository.findById(id).map(employeeEntity -> modelMapper.map(employeeEntity, EmployeeDTO.class));
    }

    public List<EmployeeDTO> getAllEmployees() {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
       return employeeEntities
               .stream()
               .map(employeeEntity -> modelMapper.map(employeeEntity, EmployeeDTO.class))
               .collect(Collectors.toList());
    }

    public EmployeeDTO createNewEmployee(EmployeeDTO inputEmployee) {
        EmployeeEntity toSaveEntity = modelMapper.map(inputEmployee, EmployeeEntity.class);
        EmployeeEntity savedEmployeeEntity =  employeeRepository.save(toSaveEntity);
        return modelMapper.map(savedEmployeeEntity, EmployeeDTO.class);
    }

    public EmployeeDTO updateEmployeeId(Long employeeId, EmployeeDTO employeeDTO) {
        EmployeeEntity employeeEntity = modelMapper.map(employeeDTO, EmployeeEntity.class);
        EmployeeEntity savedEmployeeEntity = employeeRepository.save(employeeEntity);
        return modelMapper.map(savedEmployeeEntity, EmployeeDTO.class);
    }

    public boolean isPresentByEmployeeId(Long employeeId){
        return employeeRepository.existsById(employeeId);
    }

    public boolean deleteEmployeeById(Long employeeId) {
        boolean isPresent = isPresentByEmployeeId(employeeId);
        if(!isPresent)
            return false;
        employeeRepository.deleteById(employeeId);
        return true;
    }

    public EmployeeDTO updatePartiallyEmployeeId(Long employeeId, Map<String, Object> updates) {
        boolean isPresent = isPresentByEmployeeId(employeeId);
        if(!isPresent)
          return null;
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).get();
        updates.forEach((field,value) -> {
           Field fieldToBeUpdated =  ReflectionUtils.findRequiredField(EmployeeEntity.class, field);
           fieldToBeUpdated.setAccessible(true);
           ReflectionUtils.setField(fieldToBeUpdated, employeeEntity, value);
        });
       return  modelMapper.map(employeeRepository.save(employeeEntity),EmployeeDTO.class);

    }
}

