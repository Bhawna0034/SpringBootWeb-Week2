package com.bhawna.Week2.SpringBootWeb.SpringBootWeb.repositories;

import com.bhawna.Week2.SpringBootWeb.SpringBootWeb.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
}
