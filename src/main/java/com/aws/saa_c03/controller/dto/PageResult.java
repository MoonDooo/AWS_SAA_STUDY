package com.aws.saa_c03.controller.dto;

import com.aws.saa_c03.dto.StatusCode;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PageResult<T> {
    private ResponsePage<T> data;

    private String status;
    private String statusCode;
    private String message;

    public static <T> ResponseEntity<Result<T>> isSuccess(T value){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(Result.<T>builder()
                        .data(value)
                        .status(StatusCode.OK.getHttpStatus().name())
                        .statusCode(StatusCode.OK.getCode())
                        .message(StatusCode.OK.getMessage())
                        .build()
                );
    }
    public static ResponseEntity<Object> isError(StatusCode statusCode){
        return ResponseEntity
                .status(statusCode.getHttpStatus())
                .body(Result.builder()
                        .data(null)
                        .status(statusCode.getHttpStatus().name())
                        .statusCode(statusCode.getCode())
                        .message(statusCode.getMessage())
                        .build()
                );
    }


    @Builder
    private PageResult(ResponsePage<T> data, String status, String statusCode, String message) {
        this.data = data;
        this.status = status;
        this.statusCode = statusCode;
        this.message = message;
    }
}
