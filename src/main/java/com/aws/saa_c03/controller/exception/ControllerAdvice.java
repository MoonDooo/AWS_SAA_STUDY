package com.aws.saa_c03.controller.exception;

import com.aws.saa_c03.controller.dto.Result;
import com.aws.saa_c03.dto.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerAdvice {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Object> handlerCustomException(CustomException customException){

        return Result.isError(customException.getStatusCode());
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> exceptionValid(MethodArgumentNotValidException e){
        FieldError firstError = e.getBindingResult().getFieldError();
        assert firstError != null;
        return Result.custom(HttpStatus.BAD_REQUEST, firstError.getDefaultMessage());
    }
}
