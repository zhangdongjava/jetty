package com.zzz.ehcache.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by dell_2 on 2016/8/1.
 */
@Controller
public class TestController {

    @RequestMapping("test")
    public Object test(){
        return "index";
    }
}
