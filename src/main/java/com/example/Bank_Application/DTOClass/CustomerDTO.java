package com.example.Bank_Application.DTOClass;

import com.example.Bank_Application.Entity.UserProfileEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CustomerDTO {

    private  Long id;
    @NotNull(message = "Required Type Name")
    @NotEmpty(message = "Please Enter Name")
    @Size(min = 2, max = 20)
    @Column(name = "userName",unique = true)
    private String userName;
    @NotNull(message = "Required Type Password")
    @NotEmpty(message = "Please Enter Password")
    @Size(min = 8, max = 15)
    @Column(name = "password",unique = true)
    private String password;
    @NotNull(message = "EmailID is Required")
    @NotEmpty(message = "Please Enter EmailID")
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}")
    @Column(name = "emailID",unique = true)
    private String emailID;
    @NotNull(message = "PhoneNumber Is Required")
    @Column(name = "phoneNumber",unique = true)
    private Long phoneNumber;
    private Long createAt;
    private Long loginAt;
    private  String conformPassword;
    private  String newPassword;
    private Long otpNumber;


}
