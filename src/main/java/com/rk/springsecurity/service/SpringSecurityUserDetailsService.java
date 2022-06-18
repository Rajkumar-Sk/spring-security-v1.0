package com.rk.springsecurity.service;

import com.rk.springsecurity.domain.User;
import com.rk.springsecurity.repo.SecurityServiceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Slf4j
public class SpringSecurityUserDetailsService implements UserDetailsService {
    @Autowired
    private SecurityServiceRepository securityServiceRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = securityServiceRepository.findUserByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        log.info("USER FROM MONGO DB {}", user);
        org.springframework.security.core.userdetails.User userDetails = new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(), new ArrayList<>());
        log.info("USER DETAILS {}", userDetails);
        return userDetails;
    }
}
