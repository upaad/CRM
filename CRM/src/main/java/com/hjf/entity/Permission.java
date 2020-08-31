package com.hjf.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (Permission)实体类
 *
 * @author makejava
 * @since 2020-08-19 16:44:31
 */
@Data
public class Permission implements Serializable {
    private static final long serialVersionUID = 915152889866382080L;
    
    private Integer permissionid;
    
    private String pname;
    
    private String purl;
    
    private Integer ismenu;
    
    private Integer parentid;
    
    private String pinfo;

}