package com.example.Bank_Application.Repository;

import com.example.Bank_Application.Entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepo extends JpaRepository<CustomerEntity, Long> {

//    CustomerEntity findOneByEmailIDIgnoreCaseAndPassword(String emailID, String password);



//    Query(value = "select * from customer_details where user_name = :userName",nativeQuery = true)
//    CustomerEntity updatename(String userName);

//@Query(value = "SELECT customer_details.id, customer_details.user_name, customer_details.password INNER Join userprofile ON customer_details.id = userprofile.id SET customer_details.password = password Where customer_details.user_name = :userName",nativeQuery = true)

    @Query(value = "select * from customer_details where user_name = :userName",nativeQuery = true)
    CustomerEntity update(String userName);



//    @Query(value = "select * from customer_details where emailid = :emailId",nativeQuery = true)

    CustomerEntity findByEmailID(String emailID);
    @Query(value = "select * from customer_details where otp_number = :otpNumber",nativeQuery = true)
    CustomerEntity resetPassword(Long otpNumber);
//    @Query(value = "select * from customer_details where password = :password",nativeQuery = true)
//    CustomerEntity changePwd(String password);
    @Query(value = "select * from customer_details where emailid = :emailID",nativeQuery = true)
    CustomerEntity findOneByEmailID(String emailID);
    @Query(value = "select * from customer_details where user_name = :userName",nativeQuery = true)
    CustomerEntity changePwd(String userName);


//    @Query(value = "select * from customer_details where phone_number = :phoneNumber",nativeQuery = true)
//    CustomerDTO findByPhoneNumber(Long phoneNumber);
//
//    Object findByEmailID(String emailID);

//    @Query(value = "select * from customer_details where token = :token",nativeQuery = true)
//    CustomerEntity findByToken(CustomerDTO token);
}
