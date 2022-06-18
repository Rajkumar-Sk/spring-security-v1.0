package com.rk.springsecurity.service;

import com.rk.springsecurity.domain.User;
import org.springframework.stereotype.Service;
public interface SecurityService {

    User saveUser(User user);
    User getUser(long userId);
    User updateUser(long userId);
    String deleteUser(long userId);

}
