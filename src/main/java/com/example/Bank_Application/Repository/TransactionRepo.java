package com.example.Bank_Application.Repository;

import com.example.Bank_Application.Entity.TransactionEntity;
import com.example.Bank_Application.Entity.UserProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepo extends JpaRepository<TransactionEntity,Long> {



//    @Query(value = "select * from transaction_details where account_number = :accountNumber",nativeQuery = true)
//    TransactionEntity getBalance(String accountNumber);


    @Query(value = "select * from transaction_details  where withdraw_date_time = :withdrawDate",nativeQuery = true)
    TransactionEntity getByDate(Long withdrawDate);
    @Query(value = "select * from transaction_details  where withdraw_date_time = :withdrawDate",nativeQuery = true)
    TransactionEntity getTransactionInfo(Long withdrawDate);
//    Select * from table where dates between 01/02/2012 and 29/02/2012
    @Query(value = "select * from transaction_details where date between :startDate and :endDate", nativeQuery = true)
    List<TransactionEntity> getStatementByDateAndTime(LocalDate startDate, LocalDate endDate);
}