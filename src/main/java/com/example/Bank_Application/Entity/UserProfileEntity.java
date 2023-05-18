package com.example.Bank_Application.Entity;

import com.example.Bank_Application.DTOClass.CustomerDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Cleanup;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.lang.model.element.Name;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "userprofile")
public class UserProfileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "accountNumber")
    private String accountNumber;
    @Column(name = "accountType")
    private  String accountType;
    @Column(name = "branch")
    private  String branch;
    @Column(name = "branchCode")
    private  Long branchCode;
    @Column(name = "IFSCNumber")
    private  Long  ifscNumber;
    @Column(name = "createAt")
    private  Long createAt;
    @Column(name = "updateAt")
    private  Long updateAt;
    @Column(name = "isActive")
    private boolean isActive;
    @Column(name = "isBlock")
    private boolean isBlock;
    @Column(name = "city")
    private  String city;
    @Column(name = "userId")
    private Long userId;
    @Column(name = "balance")
    private  Long balance;
    @Column(name = "initial_amount")
    private  Long initialAmount;
//    @Column(name = "toAccount")
//    private  String toAccount;
//    @Column(name = "fromAccount")
//    private String fromAccount;


}