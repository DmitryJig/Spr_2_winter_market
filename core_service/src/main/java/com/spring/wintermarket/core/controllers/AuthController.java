package com.spring.wintermarket.core.controllers;

import com.spring.winter.market.api.dtos.JwtRequest;
import com.spring.winter.market.api.dtos.JwtResponse;
import com.spring.winter.market.api.dtos.StringResponse;
import com.spring.wintermarket.core.services.UserService;
import com.spring.wintermarket.core.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
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

    @GetMapping("auth_check")
    public StringResponse authCheck(Principal principal){
        return new StringResponse(principal.getName());
    }
}
