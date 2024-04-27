package com.grocery.service;

import com.grocery.entity.UserData;

public interface UserService {

    Long save(UserData userData) throws Throwable;
}