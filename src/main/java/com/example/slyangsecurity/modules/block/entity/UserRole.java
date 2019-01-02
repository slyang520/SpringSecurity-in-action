package com.example.slyangsecurity.modules.block.entity;

import lombok.Data;

import java.util.List;

@Data
public class UserRole {

    private String userId;
    private String userName;
    private String userphone;

    // 一对多
    private List<Role> roles;

}
