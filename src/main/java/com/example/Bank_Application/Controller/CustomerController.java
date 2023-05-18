package com.example.Bank_Application.Controller;

import com.example.Bank_Application.APIResponse.ApiResponse;
import com.example.Bank_Application.DTOClass.CommonDto;
import com.example.Bank_Application.DTOClass.CustomerDTO;
import com.example.Bank_Application.DTOClass.UserProfileDTO;
import com.example.Bank_Application.Entity.CustomerEntity;
import com.example.Bank_Application.Implements.CustomerControllerImple;
import com.example.Bank_Application.Services.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/bankapi")
public class CustomerController {
    @Autowired
    private CustomerControllerImple customerService;

    @PostMapping(value = "/create")
    public ApiResponse createAccount(@RequestBody @Valid CommonDto commonDto) throws Exception {
        return customerService.createAccount(commonDto);
    }

    @PostMapping(value = "/login")
    public ApiResponse login(@RequestBody CustomerDTO customerDTO) throws Exception {
        return customerService.login(customerDTO);
    }

    @DeleteMapping(value = "/dele/{id}")
    public String delete(@PathVariable("id") CustomerEntity id) throws Exception {
        return customerService.delete(id);
    }

    @GetMapping(value = "/getall")
    public ApiResponse getAll() {
        return customerService.getAll();
    }

    @PutMapping(value = "/updateprofile")
    public ApiResponse updateProfile(@RequestBody CommonDto commonDto) throws Exception {
        return customerService.updateProfile(commonDto);
    }

    @PutMapping(value = "/changepassword")
    public ApiResponse changePassword(@RequestParam("password") String password,
                                      @RequestParam("newPassword")String newPassword,
                                      @RequestBody CommonDto commonDto) throws Exception {
        return customerService.changePassword(password,newPassword,commonDto);
    }

    @PostMapping("/forgotpassword")
    public ApiResponse forgotPassword(@RequestParam("emailID") String emailID) {
       return customerService.forgotPassword(emailID);
    }
    @PutMapping("/resetpassword")
    public ApiResponse resetPassword(@RequestBody CommonDto commonDto) throws Exception {
       return customerService.resetPassword(commonDto);
    }
    @GetMapping(value = "/getbalance/{accountnumber}")
    public ApiResponse getBalance(@PathVariable("accountnumber") String accountNumber){
        return customerService.getBalance(accountNumber);
    }
}