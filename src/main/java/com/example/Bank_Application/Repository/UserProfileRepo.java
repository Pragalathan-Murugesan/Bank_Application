package com.example.Bank_Application.Repository;

import com.example.Bank_Application.DTOClass.UserProfileDTO;
import com.example.Bank_Application.Entity.CustomerEntity;
import com.example.Bank_Application.Entity.UserProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserProfileRepo extends JpaRepository<UserProfileEntity,Long> {
    @Query(value = "select * from userprofile where user_id = :userId",nativeQuery = true)
    UserProfileEntity update(Long userId);
    @Query(value = "select * from userprofile where account_number = :accountNumber",nativeQuery = true)
    UserProfileEntity depositAmountByAccountNumber(String accountNumber);
    @Query(value = "select * from userprofile where account_number = :accountNumber",nativeQuery = true)
    UserProfileEntity withdrawByAccountNumber(String accountNumber);
    @Query(value = "select * from userprofile where account_number = :fromAccount",nativeQuery = true)
    UserProfileEntity transferData(String fromAccount);
    @Query(value = "select * from userprofile where account_number = :toAccount",nativeQuery = true)
    UserProfileEntity getTransaction(String toAccount);
    @Query(value = "select * from userprofile where account_number = :accountNumber",nativeQuery = true)
    UserProfileEntity getBalance(String accountNumber);

    @Query(value = "select * from userprofile where account_number = :accountNumber",nativeQuery = true)
    UserProfileEntity getByBalance(String accountNumber);
    @Query(value = "select * from userprofile where account_number = :accountNumber",nativeQuery = true)
    UserProfileEntity getByAccountNumberUsing(String accountNumber);
}
