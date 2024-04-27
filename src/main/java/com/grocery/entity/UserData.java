package com.grocery.entity;

import com.grocery.enums.RoleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @NotNull
    @Column(nullable = false)
    private String name;

    @NotEmpty
    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @OneToMany(mappedBy= "userData", fetch = FetchType.LAZY)
    private List<OrderData> orderData;

    private RoleType roleType;

}