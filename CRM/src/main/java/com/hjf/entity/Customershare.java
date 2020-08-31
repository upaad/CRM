package com.hjf.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (Customershare)实体类
 *
 * @author makejava
 * @since 2020-08-19 16:46:03
 */
@Data
public class Customershare implements Serializable {
    private static final long serialVersionUID = -86503022164219939L;
    
    private Integer shareid;
    
    private Integer cusid;
    
    private Integer empid;

}