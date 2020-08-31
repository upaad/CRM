package com.hjf.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UnathController {

    @RequestMapping("/unath")
    public Map Unath() {
        Map map = new HashMap();
        map.put("status","unath");
        return map;
    }
}
