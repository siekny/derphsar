package com.kshrd.derphsar_api.service.implement;


import com.kshrd.derphsar_api.repository.UserRepository;
import com.kshrd.derphsar_api.repository.dto.UserDto;
import com.kshrd.derphsar_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserDto user = this.userRepository.findByEmail(email);
        System.out.println(user);
        return user;
    }
}
