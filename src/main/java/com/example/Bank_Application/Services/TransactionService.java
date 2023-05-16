package com.example.Bank_Application.Services;

import com.example.Bank_Application.APIResponse.ApiResponse;
import com.example.Bank_Application.DTOClass.TransactionDTO;
import com.example.Bank_Application.Entity.TransactionEntity;
import com.example.Bank_Application.Implements.TransactionImple;
import com.example.Bank_Application.Repository.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.rmi.server.ExportException;
import java.time.Instant;
import java.util.List;

@Service
public class TransactionService implements TransactionImple {
    @Autowired
    private TransactionRepo transactionRepo;
    @Autowired
    private ApiResponse apiResponse;

    public ApiResponse deposite(TransactionDTO transactionDTO) throws Exception {
        try {
            TransactionEntity transactionEntity = transactionRepo.getAccountNumber(String.valueOf(transactionDTO.getAccountNumber()));
            Long epochTime = Instant.now().getEpochSecond();
            transactionEntity.setDepositDateTime(epochTime);
            transactionEntity.setDepositAmount(transactionDTO.getDepositAmount());
            Long amount = transactionDTO.getDepositAmount();
            Long balance1 = transactionEntity.getBalance();
            Long balance = 0L;
            balance = balance1 + amount;
            transactionEntity.setBalance(balance);
            transactionRepo.save(transactionEntity);

            apiResponse.setMessage("deposited Successfully");
            apiResponse.setError(HttpStatus.ACCEPTED.value());
            apiResponse.setData(balance);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return apiResponse;
    }

    @Override
    public ApiResponse withdraw(TransactionDTO transactionDTO) throws Exception {
        try {
            TransactionEntity transactionEntity = transactionRepo.getByAccountNumber(String.valueOf(transactionDTO.getAccountNumber()));
//            transactionEntity.setWithDrawAmount(transactionDTO.getWithDrawAmount());
            Long epochTime = Instant.now().getEpochSecond();
            transactionEntity.setWithdrawDateTime(epochTime);
            Long amount = transactionEntity.getWithDrawAmount();
            Long balance2 = transactionEntity.getBalance();

            if (balance2 < amount) {
                apiResponse.setMessage("Insufficient Balance");
                apiResponse.setData("null");
                apiResponse.setError(HttpStatus.NOT_FOUND.value());
            }
            if (balance2 > amount) {
                Long balance = 0L;
                balance = balance2 - amount;
                transactionEntity.setBalance(balance);
                transactionEntity.setWithDrawAmount(transactionDTO.getWithDrawAmount());
                transactionRepo.save(transactionEntity);
                apiResponse.setMessage("withdraw successfully");
                apiResponse.setError(HttpStatus.ACCEPTED.value());
                apiResponse.setData(balance);
            }
            transactionRepo.save(transactionEntity);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return apiResponse;
    }

    @Override
    public ApiResponse getAll() throws Exception {
        try {
            List<TransactionEntity> transactionEntity = transactionRepo.findAll();
            apiResponse.setError(HttpStatus.ACCEPTED.value());
            apiResponse.setData(transactionEntity);
            apiResponse.setMessage("All the data Received");
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return apiResponse;
    }

    @Override
    public ApiResponse getById(Long id) throws Exception {
        try {
            TransactionEntity transactionEntity = transactionRepo.getId(id);
            apiResponse.setMessage("Account Information");
            apiResponse.setData(transactionEntity);
            apiResponse.setError(HttpStatus.ACCEPTED.value());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return apiResponse;
    }

    @Override
    public ApiResponse getBalance(String accountNumber) throws Exception {
        try {
            TransactionEntity transactionEntity = transactionRepo.getBalance(accountNumber);
            Long balance = transactionEntity.getBalance();
            apiResponse.setMessage("Account Balance");
            apiResponse.setData(balance);
            apiResponse.setError(HttpStatus.ACCEPTED.value());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return apiResponse;
    }

    @Override
    public ApiResponse getStatement(String accountNumber) throws Exception {
        try {

            TransactionEntity transactionEntity = transactionRepo.getByStatement(accountNumber);
            transactionEntity.getAccountNumber();
            transactionEntity.getDepositDateTime();
            transactionEntity.getToAccount();
            transactionEntity.getToAccount();
            transactionEntity.getDepositAmount();
            transactionEntity.getUserId();
            transactionEntity.getWithDrawAmount();
            transactionEntity.getWithdrawAt();
            transactionEntity.getDepositBy();
            transactionEntity.getWithdrawDateTime();
            apiResponse.setData(transactionEntity);
            apiResponse.setError(HttpStatus.ACCEPTED.value());
            apiResponse.setMessage("Mini Statement");
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return apiResponse;
    }

    @Override
    public ApiResponse transaction(TransactionDTO transactionDTO) throws Exception {
        try {
            TransactionEntity transactionEntity = transactionRepo.getTransaction(transactionDTO.getToAccount());
            transactionEntity.setToAccount(transactionDTO.getToAccount());
            if (transactionDTO.getBalance() == null) {
                apiResponse.setData(null);
                apiResponse.setError(HttpStatus.NOT_FOUND);
                apiResponse.setMessage("Insufficient Balance");
            }
            TransactionEntity fromAccount = transactionRepo.transferData(transactionDTO.getFromAccount());
            transactionEntity.setFromAccount(transactionDTO.getFromAccount());
            transactionEntity.setDepositBy(transactionDTO.getDepositBy());
            transactionEntity.setDepositAmount(transactionDTO.getDepositAmount());
            String froAccount3 = (fromAccount.getFromAccount());
            Long amount = fromAccount.getDepositAmount();
            Long balance1 = fromAccount.getBalance() - amount;
            fromAccount.setBalance(balance1);
            String toAccount3 = transactionEntity.getToAccount();
            Long amt = transactionDTO.getDepositAmount();
            Long balance = transactionEntity.getBalance();
            Long balance2 = balance + amt;
            transactionEntity.setBalance(balance2);

            Long epochTime = Instant.now().getEpochSecond();
            transactionEntity.setDepositDateTime(epochTime);
            long currentTimestamp = System.currentTimeMillis();
            transactionEntity.setDeposit(currentTimestamp);
//            transactionEntity.setFromAccount(transactionEntity.getFromAccount());
//            fromAccount.setToAccount(fromAccount.getToAccount());
            transactionRepo.save(fromAccount);
            transactionRepo.save(transactionEntity);

            apiResponse.setData(transactionEntity);
            apiResponse.setMessage("Money Transfer Successfully");
            apiResponse.setError(HttpStatus.ACCEPTED.value());
        }catch (Exception e){
            throw  new Exception(e.getMessage());
        }
        return apiResponse;
    }

    @Override
    public ApiResponse withdrawInfoByDate(Long withdrawDate) throws Exception {
        try {
            TransactionEntity transactionEntity = transactionRepo.getByDate(withdrawDate);
            apiResponse.setMessage("WidthDraw Information Received");
            apiResponse.setData(transactionEntity);
            apiResponse.setError(HttpStatus.ACCEPTED.value());
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return apiResponse;
    }

    @Override
    public ApiResponse transactionInformationByDate(Long withdrawDate) throws Exception {
        try {

            TransactionEntity transactionEntity = transactionRepo.getTransactionInfo(withdrawDate);
             apiResponse.setMessage("Transaction Information Received");
             apiResponse.setError(HttpStatus.ACCEPTED.value());
             apiResponse.setData(transactionEntity);

        }catch (Exception e){
            throw  new Exception(e.getMessage());
        }
        return apiResponse;
    }

    @Override
    public ApiResponse deleteById(TransactionEntity userId) throws Exception {
        try {
           transactionRepo.delete(userId);
            apiResponse.setData(null);
            apiResponse.setError(HttpStatus.ACCEPTED.value());
            apiResponse.setMessage("userId deleted Successfully");
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return null;
    }
}

