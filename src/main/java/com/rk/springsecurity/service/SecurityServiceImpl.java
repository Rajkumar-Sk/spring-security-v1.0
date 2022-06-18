package com.rk.springsecurity.service;

import com.rk.springsecurity.domain.User;
import com.rk.springsecurity.repo.SecurityServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {
    @Autowired
    private final SecurityServiceRepository securityServiceRepository = null;

    @Override
    public User saveUser(User user) {
        return securityServiceRepository.save(user);
    }

    @Override
    public User getUser(long userId) {
        return securityServiceRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public User updateUser(long userId) {
        User user = securityServiceRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return securityServiceRepository.save(user);
    }

    @Override
    public String deleteUser(long userId) {
        return securityServiceRepository.findById(userId).isPresent() ? "User has been deleted" : "User not found";
    }
}
