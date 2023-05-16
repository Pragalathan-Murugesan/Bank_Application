package com.example.Bank_Application.Implements;

import com.example.Bank_Application.APIResponse.ApiResponse;
import com.example.Bank_Application.DTOClass.TransactionDTO;
import com.example.Bank_Application.Entity.TransactionEntity;

public interface TransactionImple {
    ApiResponse deposite(TransactionDTO transactionDTO) throws Exception;

    ApiResponse withdraw(TransactionDTO transactionDTO) throws Exception;

    ApiResponse getAll() throws Exception;

    ApiResponse getById(Long id) throws Exception;

    ApiResponse getBalance(String accountNumber) throws Exception;

    ApiResponse getStatement(String accountNumber) throws Exception;

    ApiResponse transaction(TransactionDTO transactionDTO) throws Exception;

    ApiResponse withdrawInfoByDate(Long withdrawDate) throws Exception;

    ApiResponse transactionInformationByDate(Long withdrawDate) throws Exception;

    ApiResponse deleteById(TransactionEntity userId) throws Exception;
}
