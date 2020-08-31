package com.hjf.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Map;

@Data
@Component
@ConfigurationProperties(prefix = "datapro")
public class DataProperties {

    private int num;
    private Boolean flag;
    private String str;
    private List list;
    private Map<String, String> map;
    private Role role;
}
