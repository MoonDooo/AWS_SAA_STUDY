package com.aws.saa_c03.dto;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Getter
public class CustomException extends RuntimeException{
    StatusCode statusCode;
    private static final Logger logger = LoggerFactory.getLogger(CustomException.class);

    public CustomException(StatusCode statusCode) {
        this.statusCode = statusCode;
    }

    public static CustomException of(StatusCode statusCode, String log){
        logger.warn(log);
        return new CustomException(statusCode);
    }
    public static CustomException of(StatusCode statusCode){
        return new CustomException(statusCode);
    }
}