package com.kshrd.derphsar_api.rest.restcontroller;

import com.kshrd.derphsar_api.configuration.jwtconfiguration.JwtTokenUtil;
import com.kshrd.derphsar_api.repository.dto.JwtResponse;
import com.kshrd.derphsar_api.repository.dto.UserDto;
import com.kshrd.derphsar_api.service.UserService;
import com.kshrd.derphsar_api.service.implement.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/v1")
public class JwtRestController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    UserServiceImp userServiceImp;

//    public void setUserService(UserService userService) {
//        this.userService = userService;
//    }

    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;



    @PostMapping("login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody UserDto user)throws Exception {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword()));


        }
        catch (BadCredentialsException e){
            throw new Exception("Incorrect email or password",e);

        }
        final UserDetails userDetails = userServiceImp.loadUserByUsername(user.getEmail());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

}
