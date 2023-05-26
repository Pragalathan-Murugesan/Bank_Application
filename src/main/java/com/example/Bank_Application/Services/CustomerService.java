package com.example.Bank_Application.Services;

import com.example.Bank_Application.APIResponse.ApiResponse;
import com.example.Bank_Application.DTOClass.CommonDto;
import com.example.Bank_Application.DTOClass.CustomerDTO;
import com.example.Bank_Application.Entity.CustomerEntity;
import com.example.Bank_Application.Entity.UserProfileEntity;
import com.example.Bank_Application.Implements.CustomerControllerImple;
import com.example.Bank_Application.JWT.JwtTokens;
import com.example.Bank_Application.Repository.CustomerRepo;
import com.example.Bank_Application.Repository.TransactionRepo;
import com.example.Bank_Application.Repository.UserProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class CustomerService implements CustomerControllerImple {
    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private UserProfileRepo userProfileRepo;
    @Autowired
    private TransactionRepo transactionRepo;
    @Autowired
    private ApiResponse apiResponse;
    @Autowired
    private JwtTokens jwtTokens;
    String encryptPwd;
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();


    public ApiResponse createAccount(CommonDto commonDto) throws Exception {
        String sts = "904739";
        Random random = new Random();
        String num = String.valueOf(random.nextLong(1100000000));
        String data = sts + num;
        try {
            CustomerEntity create = new CustomerEntity();
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            encryptPwd = bCryptPasswordEncoder.encode(commonDto.getPassword());
            create.setPassword(encryptPwd);
            create.setUserName(commonDto.getUserName());
            create.setEmailID(commonDto.getEmailID());
            create.setPhoneNumber(commonDto.getPhoneNumber());
            Long epochTime = Instant.now().getEpochSecond();
            create.setCreateAt(epochTime);
            customerRepo.save(create);

            UserProfileEntity add = new UserProfileEntity();
            add.setAccountNumber(data);
            add.setActive(true);
            add.setUserId(create.getId());
            Long epochTime1 = Instant.now().getEpochSecond();
            add.setCreateAt(epochTime1);
            add.setCity(commonDto.getCity());
            add.setInitialAmount(commonDto.getInitialAmount());
            Long initialAmount = commonDto.getInitialAmount();
            Long balance = 0L;
            balance += initialAmount;
            add.setBalance(balance);
            add.setBranch(commonDto.getBranch());
            add.setAccountType(commonDto.getAccountType());
            add.setBranchCode(commonDto.getBranchCode());
            add.setIfscNumber(commonDto.getIfscNumber());
            userProfileRepo.save(add);
            HashMap<String, Object> data1 = new HashMap<>();
            data1.put("table2", add);
            data1.put("table1", create);
            apiResponse.setError(HttpStatus.OK.value());
            apiResponse.setMessage("Account Created Successfully");
            apiResponse.setData(data1);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return apiResponse;
    }

    public String delete(CustomerEntity id) throws Exception {
        try {
            customerRepo.delete(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return "deleted success";
    }

    @Override
    public ApiResponse login(CustomerDTO customerDTO) throws Exception {
        try {
//            CustomerEntity customerEntity = customerRepo.findOneByEmailIDIgnoreCaseAndPassword(customerDTO.getEmailID(),customerDTO.getPassword());
            CustomerEntity customerEntity = customerRepo.findOneByEmailID(customerDTO.getEmailID());
            if (customerEntity == null) {
                apiResponse.setData("null");
                apiResponse.setMessage("Login Failed");
                apiResponse.setError(HttpStatus.BAD_REQUEST.value());
                return apiResponse;
            }
            if (bCryptPasswordEncoder.matches(customerDTO.getPassword(), customerEntity.getPassword())) {
                Long currentTimestamp = Instant.now().getEpochSecond();
                customerEntity.setLoginAt(currentTimestamp);
                customerRepo.save(customerEntity);

                String token = jwtTokens.generateToken(new CustomerEntity());
                Map<String, Object> data = new HashMap<>();
                data.put("token", token);
                apiResponse.setError(HttpStatus.OK);
                apiResponse.setMessage("Login Successfully");
                apiResponse.setData(data);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return apiResponse;
    }

    public ApiResponse getAll() {
        List<CustomerEntity> get = customerRepo.findAll();
        apiResponse.setData(get);
        apiResponse.setMessage("Data Received");
        apiResponse.setError(HttpStatus.OK.value());
        return apiResponse;
    }

    public ApiResponse updateProfile(CommonDto commonDto) throws Exception {
        try {

            CustomerEntity customerEntity1 = customerRepo.update(commonDto.getUserName());
            Long currentTimestamp1 = Instant.now().getEpochSecond();
            customerEntity1.setUpdateAt(currentTimestamp1);
            customerRepo.save(customerEntity1);

            UserProfileEntity userProfileEntity = userProfileRepo.update(commonDto.getUserId());
            userProfileEntity.setBranch(commonDto.getBranch());
            userProfileEntity.setCity(commonDto.getCity());
            userProfileEntity.setUpdateAt(currentTimestamp1);
            userProfileRepo.save(userProfileEntity);

            HashMap<String, Object> data = new HashMap<>();
            data.put("table2", userProfileEntity);
            data.put("table1", customerEntity1);
            apiResponse.setError(HttpStatus.OK.value());
            apiResponse.setData(data);
            apiResponse.setMessage("Data Updated Successfully");
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return apiResponse;
    }

    public ApiResponse changePassword(CustomerDTO customerDTO) throws Exception {
try {
            CustomerEntity customerEntity = customerRepo.changePwd(customerDTO.getUserName());
        String db =customerEntity.getPassword();

        if ( customerDTO.getPassword()!=customerDTO.getNewPassword() & bCryptPasswordEncoder.matches(customerDTO.getPassword(),db)) {
            customerDTO.setNewPassword(customerDTO.getNewPassword());
            customerDTO.setConformPassword(customerDTO.getConformPassword());
            String change = bCryptPasswordEncoder.encode(customerDTO.getNewPassword());

                 if (customerDTO.getNewPassword().equals(customerDTO.getConformPassword())) {
                        customerEntity.setPassword(change);
                        customerRepo.save(customerEntity);
                        apiResponse.setMessage("Updated Password Successfully");
                        apiResponse.setData(customerEntity);
                        apiResponse.setError(HttpStatus.OK.value());
                 }else {
                     apiResponse.setMessage("Update Password Failed");
                     apiResponse.setData(null);
                     apiResponse.setError(HttpStatus.OK.value());
                     return apiResponse;
                 }
                } else {
                apiResponse.setMessage("Please Enter Correct Password");
                apiResponse.setData(null);
                apiResponse.setError(HttpStatus.BAD_REQUEST.value());
                return apiResponse;
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return apiResponse;
    }

    public ApiResponse forgotPassword(String emailID) {
        CustomerEntity customerEntity = customerRepo.findByEmailID(emailID);
        Random random = new Random();
        String num = String.valueOf(random.nextLong(1100000000));
        Long otpNumber = Long.valueOf(num);
        customerEntity.setOtpNumber(otpNumber);
        customerRepo.save(customerEntity);
        apiResponse.setMessage(" Reset Password Otp is:");
        apiResponse.setError(HttpStatus.OK.value());
        apiResponse.setData(otpNumber);
        return apiResponse;
    }

    @Override
    public ApiResponse resetPassword(CustomerDTO customerDTO) throws Exception {
        try {
            CustomerEntity customerEntity = customerRepo.resetPassword(customerDTO.getOtpNumber());

            customerDTO.setNewPassword(customerDTO.getNewPassword());
            customerDTO.setConformPassword(customerDTO.getConformPassword());
            String change = bCryptPasswordEncoder.encode(customerDTO.getNewPassword());

            if (customerDTO.getNewPassword().equals(customerDTO.getConformPassword())) {
                customerEntity.setPassword(change);
                customerRepo.save(customerEntity);
                apiResponse.setMessage("Updated Password Successfully");
                apiResponse.setData(customerEntity);
                apiResponse.setError(HttpStatus.OK.value());
            } else {
                apiResponse.setMessage("Update Password Failed");
                apiResponse.setData(null);
                apiResponse.setError(HttpStatus.OK.value());
                return apiResponse;
            }
        }catch (Exception e){
            throw  new Exception(e.getMessage());
        }
        return apiResponse;
    }

    @Override
    public ApiResponse getBalance(String accountNumber) {
        UserProfileEntity userProfileEntity = userProfileRepo.getByBalance(accountNumber);
        Long balance = userProfileEntity.getBalance();
        apiResponse.setError(HttpStatus.OK.value());
        apiResponse.setData(balance);
        apiResponse.setMessage("This Account Balance Is:");
        return apiResponse;
    }
}