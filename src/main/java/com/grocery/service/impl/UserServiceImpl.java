package com.grocery.service.impl;

import com.grocery.entity.UserData;
import com.grocery.enums.RoleType;
import com.grocery.repository.UserDataRepository;
import com.grocery.service.UserService;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    private final UserDataRepository userRepository;

    public UserServiceImpl(UserDataRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Long save(UserData userData) throws Throwable {
        if(RoleType.ADMIN.equals(userData.getRoleType())){
            if(!Objects.isNull(userRepository.findByRoleType(userData.getRoleType()))){
                throw  new IllegalArgumentException("Admin already exist");
            }
        }
        userData = userRepository.save(userData);
        return userData.getId();
    }
}
