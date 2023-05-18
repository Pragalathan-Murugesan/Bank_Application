package com.example.Bank_Application.Controller;

import com.example.Bank_Application.APIResponse.ApiResponse;
import com.example.Bank_Application.DTOClass.TransactionDTO;
import com.example.Bank_Application.Entity.TransactionEntity;
import com.example.Bank_Application.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;


@RestController
@RequestMapping("/transactio/api")
public class TransactionController {
     @Autowired
    private TransactionService transactionService;

     @PostMapping(value = "/deposite")
    public ApiResponse  deposite(@RequestBody TransactionDTO transactionDTO) throws Exception {
         return transactionService.deposite(transactionDTO);
     }

    @PostMapping(value = "/withdra")
    public ApiResponse  withdraw(@RequestBody TransactionDTO transactionDTO) throws Exception {
        return transactionService.withdraw(transactionDTO);
    }

    @GetMapping(value = "/getall")
    public  ApiResponse getAll() throws Exception {
         return transactionService.getAll();
    }

    @GetMapping(value = "/getbyid/{accountnumber}")
    public ApiResponse getById(@PathVariable("accountnumber")String accountNumber) throws Exception {
         return transactionService.getById(accountNumber);
    }

    @GetMapping(value = "/getbalance/{accountnumber}")
    public  ApiResponse getBalance(@PathVariable("accountnumber") String accountNumber) throws Exception {
         return transactionService.getBalance(accountNumber);
    }

    @GetMapping(value = "/getstatement")
    public ApiResponse getStatements(@RequestParam("startDate")@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                      @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) throws Exception {
         return transactionService.getStatement(startDate,endDate);
    }

    @PostMapping(value = "/transaction")
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