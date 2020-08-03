package com.kshrd.derphsar_api.service;

import com.kshrd.derphsar_api.repository.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserDto findByEmail(String email);
}
