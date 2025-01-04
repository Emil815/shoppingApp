package az.example.online.shopping.infrastructure.web.handler;

import az.example.online.shopping.infrastructure.web.dto.response.ErrorResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;

@RestControllerAdvice

public class GlobalExceptionHandler {

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponseModel> handleResourceNotFound(BadCredentialsException ex) {
        return new ResponseEntity<>(new ErrorResponseModel("Telefon nömrəsi və ya şifrə səhv daxil edilib", null), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseModel> handleExceptions(Exception ex) {
        return new ResponseEntity<>(new ErrorResponseModel(ex.getMessage(), Arrays.toString(ex.getStackTrace())), HttpStatus.BAD_REQUEST);
    }
}
