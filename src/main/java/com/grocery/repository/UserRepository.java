package com.grocery.repository;

import com.grocery.entity.UserData;
import com.grocery.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserData, Long> {

    Optional<UserData> findByRoleType(RoleType roleType);
}