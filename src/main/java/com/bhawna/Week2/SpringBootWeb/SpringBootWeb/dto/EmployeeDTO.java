package com.bhawna.Week2.SpringBootWeb.SpringBootWeb.dto;

import com.bhawna.Week2.SpringBootWeb.SpringBootWeb.annotations.EmployeeRoleValidation;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {


    private Long id;
    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 10, message = "Name should be in the range: [3,10]")
    private String name;

    @Email
    private String email;

    @Max(value =80, message = "Age cannot be greater than 80")
    private Integer age;

    @NotBlank
    // @Pattern(regexp = "^(ADMIN|USER)$", message = "Role of Employee can be USER or ADMIN")
    @EmployeeRoleValidation
    private String role;

    @NotNull

    @PastOrPresent
    private LocalDate dateOfJoining;
    @JsonProperty("isActive")

    @AssertTrue(message = "Employee should be active")
    private Boolean isActive;


}
