package com.example.Bank_Application.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
    @Column(name = "date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate currentDate = LocalDate.now();
    @Column(name = "withDrawAmount")
    private Long withDrawAmount;
    @Column(name = "depositAmount")
    private  Long depositAmount;

    @Column(name = "fromAccount")
    private  String fromAccount;
    @Column(name = "toAccount")
    private String  toAccount;

    @Column(name = "withdrawAt")
    private String withdrawAt;
    @Column(name = "depositAt")
    private String depositAt;
    @Column(name = "depositBy")
    private String depositBy;
    @Column(name = "withdrawBy")
    private String withdrawBy;
    @Column(name = "depositDateTime")
    private Long depositDateTime;
    @Column(name = "withdrawDateTime")
    private Long withdrawDateTime;

}
