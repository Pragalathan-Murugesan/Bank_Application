package com.example.Bank_Application.DTOClass;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileDTO {
    private Long id;
    private Long userId;
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
    private  Long createAt;
    private  Long updateAt;
  @NotNull(message = "City is Required")
  @NotEmpty(message = "Please Enter CityName")
    private  String city;
    private String userName;
    private  Long balance;

}