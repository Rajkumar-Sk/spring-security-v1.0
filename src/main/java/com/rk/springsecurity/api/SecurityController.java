package com.rk.springsecurity.api;

import com.rk.springsecurity.domain.AuthenticationRequest;
import com.rk.springsecurity.domain.AuthenticationResponse;
import com.rk.springsecurity.domain.User;
import com.rk.springsecurity.service.SecurityServiceImpl;
import com.rk.springsecurity.service.SpringSecurityUserDetailsService;
import com.rk.springsecurity.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1.0/security")
@Slf4j
public class SecurityController {

    @Autowired
    private final SecurityServiceImpl securityServiceImpl = null;

    @Autowired
    private final SpringSecurityUserDetailsService securityUserDetailsService = null;

    @Autowired
    private final AuthenticationManager authenticationManager = null;

    @Autowired
    private final JwtUtils jwtUtils = null;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> getAuthToken(@RequestBody AuthenticationRequest authenticationRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid Username or Password");
        }
        UserDetails userDetails = securityUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        return ResponseEntity.ok(new AuthenticationResponse(jwtUtils.generateToken(userDetails)));
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, name = "ADD_USER")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        log.info("ADD USER CALL HAS BEEN STARTED");
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1.0/security/add").toUriString());
        securityServiceImpl.saveUser(user);
        return ResponseEntity.created(uri).body("User has been added " + user);
    }

    @GetMapping(value = "/get/{userId}", produces = MediaType.APPLICATION_JSON_VALUE, name = "GET_USER")
    public ResponseEntity<User> getUser(@PathVariable long userId) {
        log.info("GET USER CALL HAS BEEN STARTED");
        User user = securityServiceImpl.getUser(userId);
        return ResponseEntity.ok(user);
    }

}
