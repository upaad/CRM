package com.hjf.entity;

import lombok.Data;

import java.io.Serializable;
@Data
public class Emp implements Serializable {
    private int empid;
    private String username;
    private String password;
    private String tel;
    private String name;
    private String email;
    private int roleid;
    private String roleName;
    private String roleInfo;
}
