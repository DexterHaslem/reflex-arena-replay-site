package com.dmh.reflex.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Dexter on 7/20/2016.
 */
@RestController
public class ReplayHubController {

    @RequestMapping("/test")
    public String test(){
        return "hello!";
    }
}
