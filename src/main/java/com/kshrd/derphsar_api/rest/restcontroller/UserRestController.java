package com.kshrd.derphsar_api.rest.restcontroller;


import com.kshrd.derphsar_api.repository.dto.PromotionDto;
import com.kshrd.derphsar_api.repository.dto.ShopDto;
import com.kshrd.derphsar_api.repository.dto.UserDto;
import com.kshrd.derphsar_api.rest.BaseApiResponse;
import com.kshrd.derphsar_api.rest.message.MessageProperties;
import com.kshrd.derphsar_api.rest.promotion.request.PromotionRequestModel;
import com.kshrd.derphsar_api.rest.shop.request.ShopRequestModel;
import com.kshrd.derphsar_api.rest.user.request.UserRequestModel;
import com.kshrd.derphsar_api.rest.user.response.UserResponseModel;
import com.kshrd.derphsar_api.service.implement.UserServiceImp;
import org.apache.logging.log4j.message.Message;
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
    private MessageProperties message;


    @Autowired
    public void setMessage(MessageProperties message) {
        this.message = message;
    }

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
    public ResponseEntity<BaseApiResponse<UserRequestModel>> createUser(
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
//            response.setMessage("register successfully");
            response.setMessage(message.inserted("User"));
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
            response.setMessage(message.selected("Users"));
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


    //get one user by userId
    @GetMapping("/users/{userId}")
    public ResponseEntity<BaseApiResponse<UserResponseModel>> getOneUserById(@PathVariable String userId){
        BaseApiResponse<UserResponseModel> baseApiResponse = new BaseApiResponse<>();
        try {
            UserResponseModel userResponse = userServiceImp.getOneUserById(userId);
            baseApiResponse.setMessage(message.selectedOne("User"));
            baseApiResponse.setStatus(HttpStatus.FOUND);
            baseApiResponse.setData(userResponse);
            baseApiResponse.setTime(new Timestamp(System.currentTimeMillis()));
        }catch (Exception ex){
            baseApiResponse.setMessage(message.hasNoRecord("User"));
            baseApiResponse.setStatus(HttpStatus.NOT_FOUND);
            baseApiResponse.setData(null);
            baseApiResponse.setTime(new Timestamp(System.currentTimeMillis()));
        }
        return ResponseEntity.ok(baseApiResponse);
    }


    //delete a user by update field status
    @DeleteMapping("/users/{userId}")
    public ResponseEntity<BaseApiResponse<Void>> deleteShop(@PathVariable("userId") String userId){
        BaseApiResponse<Void> response = new BaseApiResponse<>();

        try{
            userServiceImp.deleteUserById(userId);
            response.setMessage(message.deleted("User"));
            response.setStatus(HttpStatus.OK);
            response.setTime(new Timestamp(System.currentTimeMillis()));
        }catch (Exception e){
            response.setMessage(message.deleteError("User"));
            response.setData(null);
            response.setStatus(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(response);
    }


    //update a shop
    @PutMapping("/users/{userId}")
    public ResponseEntity<BaseApiResponse<UserResponseModel>> updateUserById(
            @PathVariable("userId") String userId,
            @RequestBody UserRequestModel userRequestModel){

        ModelMapper modelMapper = new ModelMapper();
        UserDto dto = modelMapper.map(userRequestModel, UserDto.class);
        UserResponseModel responseModel = modelMapper.map(userServiceImp.updateUserById(userId,dto),UserResponseModel.class);

        BaseApiResponse<UserResponseModel> response = new BaseApiResponse <>();
        response.setMessage(message.updated("User"));
        response.setStatus(HttpStatus.OK);
        response.setData(responseModel);
        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }



}
