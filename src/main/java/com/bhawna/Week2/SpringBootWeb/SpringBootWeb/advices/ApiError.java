package com.bhawna.Week2.SpringBootWeb.SpringBootWeb.advices;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Data
@Builder

public class ApiError {

    private HttpStatus status;
    private String message;
    private List<String> subErrors;



}
