package com.dmh.reflex.controllers;

import com.dmh.reflex.db.dtos.ReplayInfoDTO;
import com.dmh.reflex.services.ReplayHubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Dexter on 7/20/2016.
 */
@RestController
public class ReplayHubController {

    @Autowired
    ReplayHubService replayHubService;

    @RequestMapping("/test")
    public String test(){
        return "hello!";
    }

    @RequestMapping(value = "/addParsed", method = RequestMethod.POST)
    public boolean addParsedReplay(@RequestBody ReplayInfoDTO replay) {
        return replayHubService.addParsedReplay(replay);
    }
}
