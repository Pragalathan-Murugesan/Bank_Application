package com.example.Bank_Application.Entity;

import com.example.Bank_Application.DTOClass.UserProfileDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customerDetails")
public class CustomerEntity  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "userName")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "emailID")
    private String emailID;
    @Column(name = "phoneNumber")
    private Long phoneNumber;
    @Column(name = "createAt")
    private Long createAt;
    @Column(name = "loginAt")
    private Long loginAt;
    @Column(name = "updateAt")
    private Long updateAt;
    @Column(name = "otpNumber")
    private Long otpNumber;


}