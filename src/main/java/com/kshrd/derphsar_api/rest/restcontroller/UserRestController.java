package com.kshrd.derphsar_api.rest.restcontroller;


import com.kshrd.derphsar_api.repository.dto.PromotionDto;
import com.kshrd.derphsar_api.repository.dto.UserDto;
import com.kshrd.derphsar_api.rest.BaseApiResponse;
import com.kshrd.derphsar_api.rest.promotion.request.PromotionRequestModel;
import com.kshrd.derphsar_api.rest.user.request.UserRequestModel;
import com.kshrd.derphsar_api.rest.user.response.UserResponseModel;
import com.kshrd.derphsar_api.service.implement.UserServiceImp;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1")
public class UserRestController {
    private UserServiceImp userServiceImp;
    private BCryptPasswordEncoder encoder;

    @Autowired
    public void setUserServiceImp(UserServiceImp userServiceImp) {
        this.userServiceImp = userServiceImp;
    }

    @Autowired
    public void setEncoder(BCryptPasswordEncoder encoder) {
        this.encoder = encoder;
    }


    // user register new account
    @PostMapping("/register")
    public ResponseEntity<BaseApiResponse<UserRequestModel>> createPromotion(
            @RequestBody UserRequestModel userRequestModel) {

        BaseApiResponse<UserRequestModel> response = new BaseApiResponse<>();
        ModelMapper mapper = new ModelMapper();

        UserDto userDto = mapper.map(userRequestModel, UserDto.class);

        try{
            userDto.setPassword(encoder.encode(userDto.getPassword()));
            userDto.setStatus(true);

            UUID uuid = UUID.randomUUID();
            userDto.setUserId("DP"+uuid.toString().substring(0,10));

            UserDto result = userServiceImp.insertUser(userDto);
            UserRequestModel result2 = mapper.map(result, UserRequestModel.class);
            response.setMessage("register successfully");
            response.setData(result2);
            response.setStatus(HttpStatus.OK);
            response.setTime(new Timestamp(System.currentTimeMillis()));
        }catch (Exception e){
            response.setMessage(e.getCause().getMessage());
            response.setStatus(HttpStatus.BAD_REQUEST);
            response.setData(null);
        }
        return ResponseEntity.ok(response);

    }


    //select all users
    @GetMapping("/users")
    public ResponseEntity<BaseApiResponse<List<UserResponseModel>>> getAllUsers(){
        BaseApiResponse<List<UserResponseModel>> response = new BaseApiResponse<>();

        try{
            List<UserResponseModel> users = userServiceImp.getAllUsers();
            response.setMessage("Users have been found successfully");
            response.setStatus(HttpStatus.OK);
            response.setData(users);
            response.setTime(new Timestamp(System.currentTimeMillis()));
        }catch (Exception e){
            response.setMessage(e.getCause().getMessage());
            response.setStatus(HttpStatus.NO_CONTENT);
            response.setData(null);
            response.setTime(new Timestamp(System.currentTimeMillis()));
        }
        return ResponseEntity.ok(response);
    }


    @GetMapping("/users/{userId}")
    public ResponseEntity<BaseApiResponse<UserResponseModel>> getOneUserById(@PathVariable String userId){
        BaseApiResponse<UserResponseModel> baseApiResponse = new BaseApiResponse<>();
        try {
            UserResponseModel userResponse = userServiceImp.getOneUserById(userId);
            baseApiResponse.setMessage("User has been found successfully");
            baseApiResponse.setStatus(HttpStatus.FOUND);
            baseApiResponse.setData(userResponse);
            baseApiResponse.setTime(new Timestamp(System.currentTimeMillis()));
        }catch (Exception ex){
            baseApiResponse.setMessage("User has been found successfully");
            baseApiResponse.setStatus(HttpStatus.NOT_FOUND);
            baseApiResponse.setData(null);
            baseApiResponse.setTime(new Timestamp(System.currentTimeMillis()));
        }
        return ResponseEntity.ok(baseApiResponse);

    }


}
