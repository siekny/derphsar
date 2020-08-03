package com.kshrd.derphsar_api.rest.restcontroller;


import com.kshrd.derphsar_api.repository.dto.PromotionDto;
import com.kshrd.derphsar_api.repository.dto.UserDto;
import com.kshrd.derphsar_api.rest.BaseApiResponse;
import com.kshrd.derphsar_api.rest.promotion.request.PromotionRequestModel;
import com.kshrd.derphsar_api.rest.user.request.UserRequestModel;
import com.kshrd.derphsar_api.service.implement.UserServiceImp;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
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
            response.setStatus(HttpStatus.CONFLICT);
            response.setData(null);
        }
        return ResponseEntity.ok(response);

    }
}
