package com.example.Bank_Application.DTOClass;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionDTO {
    private  Long id;
    private  Long balance;
    private  Long accountNumber;
    private Long withDrawAmount;
    private  Long depositAmount;
    private  Long initialAmount;
    private  String fromAccount;
    private String  toAccount;
    private Long userId;
    private Long withdrawAt;
    private Long deposit;
    private String depositBy;
    private String withdrawBy;
    private Long depositDateTime;
    private Long withdrawDateTime;
}
