package com.wavebl.addressBook.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Component
@RestControllerAdvice
@Slf4j
public class MyExceptionResolver {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<AppError> catchMyException(BusinessException exception) {
        log.error(exception.getMessage(), exception);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new AppError(exception.getMessage(), HttpStatus.BAD_REQUEST.toString()));
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<AppError> catchMyException(ValidationException exception) {
        log.error(exception.getMessage(), exception);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new AppError("Validation Error: " + exception.getMessage(), HttpStatus.BAD_REQUEST.toString()));
    }

    @ExceptionHandler
    public ResponseEntity<AppError> catchOtherException(Throwable exception) {
        log.error(exception.getMessage(), exception);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new AppError("Other Exception: " + exception.getMessage(), HttpStatus.BAD_REQUEST.toString()));
    }
}
