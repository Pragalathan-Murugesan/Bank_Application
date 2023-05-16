package com.example.Bank_Application.Repository;

import com.example.Bank_Application.Entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepo extends JpaRepository<TransactionEntity,Long> {

    @Query(value = "select * from transaction_details where account_number = :accountNumber",nativeQuery = true)
    TransactionEntity getAccountNumber(String accountNumber);

    @Query(value = "select * from transaction_details where account_number = :accountNumber",nativeQuery = true)
    TransactionEntity getByAccountNumber(String accountNumber);
    @Query(value = "select * from transaction_details where id = :id",nativeQuery = true)
    TransactionEntity getId(Long id);
    @Query(value = "select * from transaction_details where account_number = :accountNumber",nativeQuery = true)
    TransactionEntity getBalance(String accountNumber);
    @Query(value = "select * from transaction_details where account_number = :accountNumber",nativeQuery = true)
    TransactionEntity getByStatement(String accountNumber);


    @Query(value = "select * from transaction_details  where account_number = :toAccount",nativeQuery = true)
    TransactionEntity getTransaction(String toAccount);
    @Query(value = "select * from transaction_details  where account_number = :fromAccount",nativeQuery = true)
    TransactionEntity transferData(String fromAccount);
    @Query(value = "select * from transaction_details  where withdraw_date_time = :withdrawDate",nativeQuery = true)
    TransactionEntity getByDate(Long withdrawDate);
    @Query(value = "select * from transaction_details  where withdraw_date_time = :withdrawDate",nativeQuery = true)
    TransactionEntity getTransactionInfo(Long withdrawDate);
}
