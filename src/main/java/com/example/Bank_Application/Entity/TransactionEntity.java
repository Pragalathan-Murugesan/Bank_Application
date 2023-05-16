package com.example.Bank_Application.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transactionDetails")
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private  Long id;
    @Column(name = "balance")
    private  Long balance;
    @Column(name = "accountNumber")
    private  String accountNumber;
    @Column(name = "withDrawAmount")
    private Long withDrawAmount;
    @Column(name = "depositAmount")
    private  Long depositAmount;
    @Column(name = "initial_amount")
    private  Long initialAmount;
    @Column(name = "fromAccount")
    private  String fromAccount;
    @Column(name = "toAccount")
    private String  toAccount;
    @Column(name = "userId")
    private Long userId;
    @Column(name = "withdrawAt")
    private Long withdrawAt;
    @Column(name = "depositAt")
    private Long deposit;
    @Column(name = "depositBy")
    private String depositBy;
    @Column(name = "withdrawBy")
    private String withdrawBy;
    @Column(name = "depositDateTime")
    private Long depositDateTime;
    @Column(name = "withdrawDateTime")
    private Long withdrawDateTime;


}
