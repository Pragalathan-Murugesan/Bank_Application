package com.example.Bank_Application.Implements;

import com.example.Bank_Application.APIResponse.ApiResponse;
import com.example.Bank_Application.DTOClass.CommonDto;
import com.example.Bank_Application.DTOClass.CustomerDTO;
import com.example.Bank_Application.DTOClass.UserProfileDTO;
import com.example.Bank_Application.Entity.CustomerEntity;

import java.text.ParseException;

public interface CustomerControllerImple {


    String delete(CustomerEntity id) throws Exception;

    ApiResponse login(CustomerDTO customerDTO) throws Exception;

    ApiResponse getAll();

    ApiResponse createAccount(CommonDto commonDto) throws Exception;

    ApiResponse updateProfile(CommonDto commonDto)throws Exception;



    ApiResponse forgotPassword(String emailID);

    ApiResponse resetPassword(CustomerDTO customerDTO) throws Exception;

    ApiResponse getBalance(String accountNumber);

    ApiResponse changePassword( CustomerDTO customerDTO) throws Exception;
}
