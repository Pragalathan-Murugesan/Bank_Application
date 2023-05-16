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




//    Object updatePro(String accountNumber);
//    @Query(value = "select * from userprofile where account_number = :accountNumber",nativeQuery = true)
//    UserProfileDTO updatePro(String accountNumber);
}
