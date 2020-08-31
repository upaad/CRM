package com.hjf.entity;

import com.hjf.annotation.DataValidator;
import lombok.Data;

import javax.validation.constraints.Pattern;
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

    @DataValidator(message = "用户不能为空")
    private String cusname;
    
    private String address;
    
    private String contact;

    @DataValidator(message = "电话号码不符合要求")
    private String tel;
    
    private String email;

    private Integer empid;

}