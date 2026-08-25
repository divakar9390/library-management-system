package com.example.exception;


import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GobalExceptionhandler  {
    @ExceptionHandler(ResourcesNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlerResorceNotFound(ResourcesNotFoundException ex,WebRequest request){

        ErrorResponse error = new ErrorResponse(
               LocalDateTime.now(),
               HttpStatus.NOT_FOUND.value(),
               "Not Found",
               ex.getMessage(),
              request.getDescription(false).replace("uri=", "")


        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);

    }

    @ExceptionHandler(DuplicateResourcesException.class)
    public ResponseEntity<ErrorResponse> handlerDuplicate(DuplicateResourcesException ex,WebRequest request){

        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.CONFLICT.value(),
                "Conflict",
                ex.getMessage(),
                request.getDescription(false).replace("uri=", "")
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<ErrorResponse> handlerValidation(
                MethodArgumentNotValidException ex,
                WebRequest request) {

            String message = ex.getBindingResult()
                    .getFieldErrors()
                    .stream()
                    .map(error ->
                            error.getField() + ": " + error.getDefaultMessage()
                    )
                    .collect(Collectors.joining(", "));

            ErrorResponse error = new ErrorResponse(
                    LocalDateTime.now(),
                    HttpStatus.BAD_REQUEST.value(),
                    "Bad Request",
                    message,
                    request.getDescription(false).replace("uri=", "")
            );

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error);
        }

        @ExceptionHandler(Exception.class)
        public ResponseEntity<ErrorResponse> handlerInternalServer(Exception ex , WebRequest request){
                ErrorResponse error = new ErrorResponse(
                        LocalDateTime.now(),
                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        "Internal Server Error",
                        "An Unexpected Error Occured",
                        request.getDescription(false).replace("uri=",""));

                 return ResponseEntity
                     .status(HttpStatus.INTERNAL_SERVER_ERROR)
                     .body(error);
        }
    
}
