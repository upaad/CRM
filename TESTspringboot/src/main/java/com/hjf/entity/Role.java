package com.hjf.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (Role)实体类
 *
 * @author makejava
 * @since 2020-08-19 16:41:27
 */
@Data
public class Role implements Serializable {
    private static final long serialVersionUID = 462992752340634513L;
    
    private Integer roleid;
    
    private String rolename;
    
    private String roleinfo;

}