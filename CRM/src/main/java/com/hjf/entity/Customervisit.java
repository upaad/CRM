package com.hjf.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.io.Serializable;

/**
 * (Customervisit)实体类
 *
 * @author makejava
 * @since 2020-08-19 16:46:12
 */
@Data
public class Customervisit implements Serializable {
    private static final long serialVersionUID = 879306574705102529L;
    
    private Integer visitid;
    
    private Integer cusid;
    
    private Integer empid;
    
    private String content;

//    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date date;

}