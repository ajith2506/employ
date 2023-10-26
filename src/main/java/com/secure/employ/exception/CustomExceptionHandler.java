//package com.springboot.employ_security.exception;
//
//import com.springboot.employ_security.util.ErrorResponse;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//@ControllerAdvice
//public class CustomExceptionHandler {
//
//    @ExceptionHandler(UsernameAlreadyExistsException.class)
//    public ResponseEntity<Object> handleUsernameAlreadyExistsException(UsernameAlreadyExistsException e) {
//        // Customize the error response for UsernameAlreadyExistsException
//        ErrorResponse errorResponse = new ErrorResponse("Username already exists", HttpStatus.BAD_REQUEST);
//        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(EmployeeNotFoundException.class)
//    public ResponseEntity<Object> handleEmployeeNotFoundException(EmployeeNotFoundException e) {
//        // Customize the error response for EmployeeNotFoundException
//        ErrorResponse errorResponse = new ErrorResponse("Employee not found", HttpStatus.NOT_FOUND);
//        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
//    }
//
//    @ExceptionHandler(InvalidCredentialsException.class)
//    public ResponseEntity<Object> handleInvalidCredentialsException(InvalidCredentialsException e){
//        ErrorResponse errorResponse = new ErrorResponse("Invalid Credentials",HttpStatus.UNAUTHORIZED);
//        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
//    }
//
//
//}
//
