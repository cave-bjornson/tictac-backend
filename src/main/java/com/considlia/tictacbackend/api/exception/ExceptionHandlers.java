package com.considlia.tictacbackend.api.exception;

import com.considlia.tictacbackend.service.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice(annotations = RestController.class)
class ExceptionHandlers {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> defaultException(final Exception exception, final WebRequest request) throws Exception {
        String message = exception.getCause() == null ? exception.getMessage() : exception.getCause().getMessage();
        ErrorResponse response = ErrorResponse.builder()
                                              .withStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                                              .withMessage(message)
                                              .build();
        System.out.println(exception.toString());
        return new ResponseEntity<>(response, response.getStatus());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> resourceNotFoundException(final ResourceNotFoundException exception, final WebRequest request) {
        String message = exception.getCause() == null ? exception.getMessage() : exception.getCause().getMessage();
        ErrorResponse response = ErrorResponse.builder()
                                              .withStatus(HttpStatus.NOT_FOUND)
                                              .withMessage(message)
                                              .build();

        return new ResponseEntity<>(response, response.getStatus());
    }
}
