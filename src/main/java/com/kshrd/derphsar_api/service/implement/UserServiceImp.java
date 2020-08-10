package com.kshrd.derphsar_api.service.implement;


import com.kshrd.derphsar_api.repository.UserRepository;
import com.kshrd.derphsar_api.repository.dto.UserDto;
import com.kshrd.derphsar_api.rest.role.response.RoleResponse;
import com.kshrd.derphsar_api.rest.user.request.UserRequestModel;
import com.kshrd.derphsar_api.rest.user.response.UserResponseModel;
import com.kshrd.derphsar_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService {
    private UserRepository userRepository;


    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserDto insertUser(UserDto userDto) {
        userRepository.insertUser(userDto);
        userRepository.insertUserRole(userDto);
        return userDto;
    }

    @Override
    public List<UserResponseModel> getAllUsers() {
        List<UserResponseModel> list = userRepository.getAllUsers();
        List<RoleResponse> roleResponse;
        for(int i=0 ;i<list.size(); i++){
            roleResponse = userRepository.role(list.get(i).getId());
            list.get(i).setRole(roleResponse);
        }
        return list;
    }

    @Override
    public UserResponseModel getOneUserById(String userId) {
        UserResponseModel userResponseModel = userRepository.getOneUserById(userId);
        List<RoleResponse> roleResponses;
        roleResponses = userRepository.role(userResponseModel.getId());
        userResponseModel.setRole(roleResponses);
        return userResponseModel;
    }

    @Override
    public void deleteUserById(String userId) {
        userRepository.deleteOneUserById(userId);
    }

    @Override
    public UserDto updateUserById(String userId, UserDto userDto) {
        Boolean isUpdated = userRepository.updateUserById(userId, userDto);
        if(isUpdated)
            return userDto;
        else
            return null;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserDto user = this.userRepository.findByEmail(email);
       //System.out.println(user);
        return user;
    }
}
