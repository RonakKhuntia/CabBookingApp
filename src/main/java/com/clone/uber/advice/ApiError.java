package com.clone.uber.advice;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@Builder
public class ApiError {

    //ApiError
    private HttpStatus status;
    private String message;
    private List<String> subErrors;

}
