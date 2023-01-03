package com.spring.wintermarket.controllers;

import com.spring.wintermarket.dtos.JwtRequest;
import com.spring.wintermarket.dtos.JwtResponse;
import com.spring.wintermarket.services.UserService;
import com.spring.wintermarket.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final DaoAuthenticationProvider authenticationManager;

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest autRequest){
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(autRequest.getUsername(), autRequest.getPassword()));
        } catch (BadCredentialsException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        UserDetails userDetails = userService.loadUserByUsername(autRequest.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

//    @GetMapping("/secured")
//    public String helloSecurity(){
//        return "Hello";
//    }
}
