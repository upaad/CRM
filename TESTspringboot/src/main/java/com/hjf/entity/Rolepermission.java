package com.hjf.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (Rolepermission)实体类
 *
 * @author makejava
 * @since 2020-08-19 16:45:26
 */
@Data
public class Rolepermission implements Serializable {
    private static final long serialVersionUID = 326193646001628569L;
    
    private Integer rpid;
    
    private Integer roleid;
    
    private Integer permissionid;

    private String pname;

    private String roleName;
}