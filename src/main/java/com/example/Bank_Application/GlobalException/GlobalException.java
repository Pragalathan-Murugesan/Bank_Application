package com.example.Bank_Application.GlobalException;

import com.example.Bank_Application.APIResponse.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice
public class GlobalException {
    @Autowired
    private ApiResponse apiResponse;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse methodArgumentNotValidException(MethodArgumentNotValidException e) {
     HashMap<Object,String> errors = new HashMap<>();
     e.getBindingResult().getFieldErrors().forEach((error) ->{
         String fieldName = error.getField();
         String message = error.getDefaultMessage();
          errors.put(fieldName,message);
          apiResponse.setError(errors);
          apiResponse.setData(HttpStatus.BAD_REQUEST.value());
          apiResponse.setMessage("Something Went To Wrong");
        });
     return apiResponse;
    }

//       @ResponseStatus(HttpStatus.UNAUTHORIZED)
//    @ExceptionHandler(Exception.class)
//    public ApiResponse handleException(Exception e){
//
//        apiResponse.setError(HttpStatus.BAD_REQUEST.value());
//        apiResponse.setMessage("Unauthorized Access");
//        apiResponse.setData("null");
//        return apiResponse;
//   }
//    @ExceptionHandler(IllegalArgumentException.class)
//    public ApiResponse IllegalException(IllegalArgumentException e){
//        apiResponse.setMessage("Unauthorized Access");
//        apiResponse.setData(null);
//        apiResponse.setError(HttpStatus.UNAUTHORIZED.value());
//        return apiResponse;
//    }
    }

