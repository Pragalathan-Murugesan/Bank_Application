package com.example.Bank_Application.DTOClass;

import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TransactionDTO {
    private  Long id;
    private LocalDate currentDate;

    private Long withDrawAmount;
    private  Long depositAmount;
    private  String fromAccount;
    private String  toAccount;

    private String withdrawAt;
    private String depositAt;
    private String depositBy;
    private String withdrawBy;
    private Long depositDateTime;
    private Long withdrawDateTime;
    private  String accountNumber;
    private  Long balance;
}
