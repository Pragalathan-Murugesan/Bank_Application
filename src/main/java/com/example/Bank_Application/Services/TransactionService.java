package com.example.Bank_Application.Services;

import com.example.Bank_Application.APIResponse.ApiResponse;
import com.example.Bank_Application.DTOClass.TransactionDTO;
import com.example.Bank_Application.Entity.TransactionEntity;
import com.example.Bank_Application.Entity.UserProfileEntity;
import com.example.Bank_Application.Implements.TransactionImple;
import com.example.Bank_Application.Repository.TransactionRepo;
import com.example.Bank_Application.Repository.UserProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService implements TransactionImple {

    @Autowired
    private TransactionRepo transactionRepo;
    @Autowired
    private ApiResponse apiResponse;
    @Autowired
    private UserProfileRepo userProfileRepo;

    public ApiResponse deposite(TransactionDTO transactionDTO) throws Exception {
        try {
            UserProfileEntity userProfileEntity= userProfileRepo.depositAmountByAccountNumber(transactionDTO.getAccountNumber());
            TransactionEntity transactionEntity1 = new TransactionEntity();
            Long epochTime = Instant.now().getEpochSecond();
            transactionEntity1.setDepositDateTime(epochTime);

            LocalTime currentTime = LocalTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
            String formattedTime = currentTime.format(formatter);
            transactionEntity1.setDepositAt(formattedTime);

            Long amount = transactionDTO.getDepositAmount();
            Long balance1 = userProfileEntity.getBalance();

                Long balance = 0L;
                balance = balance1 + amount;
                userProfileEntity.setBalance(balance);
                transactionEntity1.setDepositAmount(transactionDTO.getDepositAmount());
                transactionEntity1.setDepositBy(transactionDTO.getDepositBy());
                apiResponse.setMessage("deposited Successfully");
                apiResponse.setError(HttpStatus.ACCEPTED.value());
                apiResponse.setData(balance);
                userProfileRepo.save(userProfileEntity);
                transactionRepo.save(transactionEntity1);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return apiResponse;
    }

    @Override
    public ApiResponse withdraw(TransactionDTO transactionDTO) throws Exception {
        try {
            UserProfileEntity userProfileEntity = userProfileRepo.withdrawByAccountNumber(transactionDTO.getAccountNumber());
            TransactionEntity transactionEntity2 = new TransactionEntity();

            Long epochTime = Instant.now().getEpochSecond();
            transactionEntity2.setWithdrawDateTime(epochTime);

            LocalTime currentTime = LocalTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
            String formattedTime = currentTime.format(formatter);
            transactionEntity2.setWithdrawAt(formattedTime);

            transactionEntity2.setWithDrawAmount(transactionDTO.getWithDrawAmount());
            transactionEntity2.setWithdrawBy(transactionDTO.getWithdrawBy());
            Long amount = transactionDTO.getWithDrawAmount();
            Long balance2 = userProfileEntity.getBalance();

            if (balance2 <= 500) {
                apiResponse.setMessage("Insufficient Balance");
                apiResponse.setData("null");
                apiResponse.setError(HttpStatus.BAD_REQUEST.value());
            }
            if (balance2 > 500) {
                Long balance = 0L;
                balance = balance2 - amount;
                userProfileEntity.setBalance(balance);
                userProfileRepo.save(userProfileEntity);
                apiResponse.setMessage("withdraw successfully");
                apiResponse.setError(HttpStatus.OK.value());
                apiResponse.setData(balance);
            }
            transactionRepo.save(transactionEntity2);

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return apiResponse;
    }

    @Override
    public ApiResponse getAll() throws Exception {
        try {
            List<TransactionEntity> transactionEntity = transactionRepo.findAll();
            apiResponse.setError(HttpStatus.OK.value());
            apiResponse.setData(transactionEntity);
            apiResponse.setMessage("All the data Received");
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return apiResponse;
    }

    @Override
    public ApiResponse getById(String accountNumber) throws Exception {
        try {
            UserProfileEntity userProfileEntity = userProfileRepo.getByAccountNumberUsing(accountNumber);
            apiResponse.setMessage("Account Information");
            apiResponse.setData(userProfileEntity);
            apiResponse.setError(HttpStatus.OK.value());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return apiResponse;
    }

    @Override
    public ApiResponse getBalance(String accountNumber) throws Exception {
        try {
            UserProfileEntity userProfileEntity = userProfileRepo.getBalance(accountNumber);
            Long balance = userProfileEntity.getBalance();
            apiResponse.setMessage("Account Balance");
            apiResponse.setData(balance);
            apiResponse.setError(HttpStatus.OK.value());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return apiResponse;
    }

    @Override
    public ApiResponse getStatement(LocalDate startDate,LocalDate endDate) throws Exception {
        try {
            List<TransactionEntity> transactionEntity = transactionRepo.getStatementByDateAndTime(startDate,endDate);

            apiResponse.setData(transactionEntity);
            apiResponse.setError(HttpStatus.OK.value());
            apiResponse.setMessage("Mini Statement");
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return apiResponse;
    }

    @Override
    public ApiResponse transaction(TransactionDTO transactionDTO) throws Exception {
        try {
            UserProfileEntity fromAccount = userProfileRepo.transferData(transactionDTO.getFromAccount());
            UserProfileEntity userProfileEntity = userProfileRepo.getTransaction(transactionDTO.getToAccount());
            TransactionEntity transactionEntity = new TransactionEntity();
            transactionEntity.setToAccount(transactionDTO.getToAccount());
            transactionEntity.setFromAccount(transactionDTO.getFromAccount());
            transactionEntity.setDepositBy(transactionDTO.getDepositBy());
            transactionEntity.setDepositAmount(transactionDTO.getDepositAmount());

       Long balance = fromAccount.getBalance();
            String froAccount3 = (transactionEntity.getFromAccount());
            if (balance<=500) {
                apiResponse.setMessage("Insufficient Balance");
                apiResponse.setData(null);
                apiResponse.setError(HttpStatus.BAD_REQUEST.value());
            }
                if (balance>500) {
                    Long amount = transactionEntity.getDepositAmount();
                    Long balance1 = 0L;
                    balance1 = balance - amount;
                    fromAccount.setBalance(balance1);
                }
            String toAccount3 = transactionEntity.getToAccount();
            Long balance3 = userProfileEntity.getBalance();
            if (balance>500) {
                Long amt = transactionDTO.getDepositAmount();
                Long balance2 = 0L;
                balance2 =balance3 + amt;
                userProfileEntity.setBalance(balance2);
                apiResponse.setData(userProfileEntity);
                apiResponse.setMessage("Money Transfer Successfully");
                apiResponse.setError(HttpStatus.OK.value());
            }
            Long epochTime = Instant.now().getEpochSecond();
            transactionEntity.setDepositDateTime(epochTime);

              userProfileRepo.save(fromAccount);
              transactionRepo.save(transactionEntity);

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
            apiResponse.setError(HttpStatus.OK.value());
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
             apiResponse.setError(HttpStatus.OK.value());
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
            apiResponse.setError(HttpStatus.OK.value());
            apiResponse.setMessage("userId deleted Successfully");
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return apiResponse;
    }
}

