package com.grocery.service.impl;

import com.grocery.entity.UserData;
import com.grocery.enums.RoleType;
import com.grocery.repository.UserRepository;
import com.grocery.service.UserService;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Long save(UserData userData) throws Throwable {
        if(RoleType.ADMIN.equals(userData.getRoleType())){
            userRepository.findByRoleType(userData.getRoleType()).orElseThrow(() ->
                    new IllegalArgumentException("Admin already exist"));
        }
        userData = userRepository.save(userData);
        return userData.getId();
    }
}
