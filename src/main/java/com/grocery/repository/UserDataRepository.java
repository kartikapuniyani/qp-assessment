package com.grocery.repository;

import com.grocery.entity.UserData;
import com.grocery.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDataRepository extends JpaRepository<UserData, Long> {

    UserData findByRoleType(RoleType roleType);
}