package com.example.Bank_Application.Services;

import com.example.Bank_Application.APIResponse.ApiResponse;
import com.example.Bank_Application.DTOClass.CommonDto;
import com.example.Bank_Application.DTOClass.CustomerDTO;
import com.example.Bank_Application.DTOClass.TransactionDTO;
import com.example.Bank_Application.Entity.CustomerEntity;
import com.example.Bank_Application.Entity.TransactionEntity;
import com.example.Bank_Application.Entity.UserProfileEntity;
import com.example.Bank_Application.Implements.CustomerControllerImple;
import com.example.Bank_Application.JWT.JwtTokens;
import com.example.Bank_Application.Repository.CustomerRepo;
import com.example.Bank_Application.Repository.TransactionRepo;
import com.example.Bank_Application.Repository.UserProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.*;

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


    public ApiResponse createAccount(CommonDto commonDto) throws Exception {
        TransactionDTO transactionDTO  = new TransactionDTO();
        String sts = "904739";
        Random random = new Random();
        String num = String.valueOf(random.nextLong(1100000000));
        String data = sts + num;
        try {
            CustomerEntity create = new CustomerEntity();
            create.setUserName(commonDto.getUserName());
            create.setPassword(commonDto.getPassword());
            create.setEmailID(commonDto.getEmailID());
            create.setPhoneNumber(commonDto.getPhoneNumber());


            Long epochTime = Instant.now().getEpochSecond();
            create.setCreateAt(epochTime);
            customerRepo.save(create);

            UserProfileEntity add = new UserProfileEntity();
            add.setAccountNumber(data);
            if (add.getAccountNumber()!=null){
                add.setActive(true);
            }else{
                add.setBlock(false);
            }
            add.setUserId(create.getId());
            Long epochTime1 = Instant.now().getEpochSecond();
            add.setCreateAt(epochTime1);
            add.setCity(commonDto.getCity());

            add.setBranch(commonDto.getBranch());
            add.setAccountType(commonDto.getAccountType());
            add.setBranchCode(commonDto.getBranchCode());
            add.setIfscNumber(commonDto.getIfscNumber());
            userProfileRepo.save(add);

            TransactionEntity transactionEntity = new TransactionEntity();
            transactionEntity.setUserId(create.getId());
            transactionEntity.setAccountNumber(add.getAccountNumber());
            transactionEntity.setInitialAmount(commonDto.getInitialAmount());
//            transactionEntity.setBalance(commonDto.getInitialAmount());
            Long initialAmount = transactionEntity.getInitialAmount();
//            Long balance2 = commonDto.getBalance();
            Long balance = 0L;
            balance +=  initialAmount;
            transactionEntity.setBalance(balance);
            transactionRepo.save(transactionEntity);
            HashMap<String, Object> data1 = new HashMap<>();
            data1.put("table2", add);
            data1.put("table1", create);
            apiResponse.setError(HttpStatus.ACCEPTED.value());
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
            CustomerEntity customerEntity = customerRepo.findOneByEmailIDIgnoreCaseAndPassword(customerDTO.getEmailID(), customerDTO.getPassword());
            if (customerEntity == null) {
                apiResponse.setData("null");
                apiResponse.setMessage("Login Failed");
                apiResponse.setError(HttpStatus.NOT_FOUND.value());
                return apiResponse;
            }
            Long currentTimestamp = Instant.now().getEpochSecond();
            customerEntity.setLoginAt(currentTimestamp);
            customerRepo.save(customerEntity);

            Long epochTime1 = Instant.now().getEpochSecond();
            customerEntity.setUpdateAt(epochTime1);
            customerRepo.save(customerEntity);

            String token = jwtTokens.generateToken(new CustomerEntity());
            Map<String, Object> data = new HashMap<>();
            data.put("token", token);
            apiResponse.setError(HttpStatus.ACCEPTED);
            apiResponse.setMessage("Login Successfully");
            apiResponse.setData(data);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return apiResponse;
    }

    public ApiResponse getAll() {
        List<CustomerEntity> get = customerRepo.findAll();

        apiResponse.setData(get);
        apiResponse.setMessage("Data Received");
        apiResponse.setError(HttpStatus.ACCEPTED.value());
        return apiResponse;
    }

    public ApiResponse updateProfile(CommonDto commonDto) throws Exception {
        try {

            CustomerEntity customerEntity1 = customerRepo.update(commonDto.getUserName());
            customerEntity1.setPassword(commonDto.getPassword());
            Long currentTimestamp1 = Instant.now().getEpochSecond();
            customerEntity1.setUpdateAt(currentTimestamp1);
            customerRepo.save(customerEntity1);

            UserProfileEntity userProfileEntity = userProfileRepo.update(commonDto.getUserId());
            userProfileEntity.setBranch(commonDto.getBranch());
            userProfileEntity.setCity(commonDto.getCity());
            Long currentTimestamp = Instant.now().getEpochSecond();
            userProfileEntity.setUpdateAt(currentTimestamp1);
            userProfileRepo.save(userProfileEntity);

            HashMap<String, Object> data = new HashMap<>();
            data.put("table2", userProfileEntity);
            data.put("table1", customerEntity1);
            apiResponse.setError(HttpStatus.ACCEPTED.value());
            apiResponse.setData(data);
            apiResponse.setMessage("Data Updated Successfully");
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return apiResponse;
    }

    public ApiResponse changePassword(CommonDto commonDto) throws Exception {
        try {
            CustomerEntity customerEntity = customerRepo.changePwd(commonDto.getUserName());
            customerEntity.setPassword(commonDto.getPassword());
            customerRepo.save(customerEntity);
            apiResponse.setMessage("Password Updated Successfully");
            apiResponse.setData(customerEntity);
            apiResponse.setError(HttpStatus.ACCEPTED.value());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return apiResponse;
    }
    public ApiResponse requestPasswordReset(String emailID) {
        CustomerEntity customerEntity = customerRepo.findByEmailID(emailID);
        Random random = new Random();
        String num = String.valueOf(random.nextLong(1100000000));
        Long otpNumber = Long.valueOf(num);
        apiResponse.setMessage(" Reset Password Successfully");
        apiResponse.setError(HttpStatus.ACCEPTED.value());
        apiResponse.setData(otpNumber);
        return apiResponse;
    }
}