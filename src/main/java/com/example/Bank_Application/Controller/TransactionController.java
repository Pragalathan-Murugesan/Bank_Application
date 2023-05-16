package com.example.Bank_Application.Controller;

import com.example.Bank_Application.APIResponse.ApiResponse;
import com.example.Bank_Application.DTOClass.TransactionDTO;
import com.example.Bank_Application.Entity.TransactionEntity;
import com.example.Bank_Application.Services.TransactionService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactio/api")
public class TransactionController {
     @Autowired
    private TransactionService transactionService;

     @PutMapping(value = "/deposite")
    public ApiResponse  deposite(@RequestBody TransactionDTO transactionDTO) throws Exception {
         return transactionService.deposite(transactionDTO);
     }
    @PutMapping(value = "/withdra")
    public ApiResponse  withdraw(@RequestBody TransactionDTO transactionDTO) throws Exception {
        return transactionService.withdraw(transactionDTO);
    }

    @GetMapping(value = "/getall")
    public  ApiResponse getAll() throws Exception {
         return transactionService.getAll();
    }
    @GetMapping(value = "/getbyid/{id}")
    public ApiResponse getById(@PathVariable("id")Long id) throws Exception {
         return transactionService.getById(id);
    }
    @GetMapping(value = "/getbalance/{accountnumber}")
    public  ApiResponse getBalance(@PathVariable("accountnumber") String accountNumber) throws Exception {
         return transactionService.getBalance(accountNumber);
    }
    @GetMapping(value = "/getstatement/{accountnumber}")
    public ApiResponse getStatements(@PathVariable("accountnumber") String accountNumber) throws Exception {
         return transactionService.getStatement(accountNumber);
    }
    @PutMapping(value = "/transaction")
    public ApiResponse transaction(@RequestBody TransactionDTO transactionDTO) throws Exception {
         return transactionService.transaction(transactionDTO);
    }
    @GetMapping(value = "/withdrawinfobydate/{withdrawdate}")
    public ApiResponse withdrawInfoByDate(@PathVariable("withdrawdate") Long withdrawDate ) throws Exception {
         return transactionService.withdrawInfoByDate(withdrawDate);
    }

    @GetMapping(value = "/transactioninformation/{withdrawdate}")
    public ApiResponse transactionInformationByDate(@PathVariable("withdrawdate") Long withdrawDate ) throws Exception {
        return transactionService.transactionInformationByDate(withdrawDate);
    }
    @DeleteMapping(value = "/deletebyid/{userid}")
    public ApiResponse deleteById(@PathVariable("userid") TransactionEntity userId) throws Exception {
         return transactionService.deleteById(userId);
    }
}