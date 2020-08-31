package com.hjf.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (Customer)实体类
 *
 * @author makejava
 * @since 2020-08-19 16:45:49
 */
@Data
public class Customer implements Serializable {
    private static final long serialVersionUID = 676735929324173099L;
    
    private Integer cusid;
    
    private String cusname;
    
    private String address;
    
    private String contact;
    
    private String tel;
    
    private String email;

    private Integer empid;

}