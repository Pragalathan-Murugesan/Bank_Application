package com.example.Bank_Application.DTOClass;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonDto {



    private  Long id;
    @NotNull(message = "Required Type Name")
    @NotEmpty(message = "Please Enter Name")
    @Size(min = 2, max = 20)
    private String userName;
    @NotNull(message = "Required Type Password")
    @NotEmpty(message = "Please Enter Password")
    @Size(min = 8, max = 15)
    private String password;
    @NotNull(message = "EmailID is Required")
    @NotEmpty(message = "Please Enter EmailID")
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}")
    private String emailID;
    @NotNull(message = "PhoneNumber Is Required")
    private Long phoneNumber;
    private Long createAt;
    private Long loginAt;





    @NotNull(message = "AccountType Is Required")
    @NotEmpty(message = "please enter AccountType")
    private  String accountType;
    private String accountNumber;
    @NotNull(message = "Branch Is Required")
    @NotEmpty(message = "please enter branch name")
    private  String branch;
    @NotNull(message = "Branch Code Is Required")
    private  Long branchCode;
    @NotNull(message = "IFSCNumber is Required")
    private  Long  ifscNumber;
    private  Long updateAt;
    @NotNull(message = "City is Required")
    @NotEmpty(message = "Please Enter CityName")
    private  String city;
    private  Boolean isBlock;
    private  Boolean isActive;
    private Long userId;
    private Long initialAmount;
    private Long balance;
}
