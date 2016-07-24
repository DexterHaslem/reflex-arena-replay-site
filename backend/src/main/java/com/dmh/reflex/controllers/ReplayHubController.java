package com.dmh.reflex.controllers;

import com.dmh.reflex.db.dtos.ReplayInfoDTO;
import com.dmh.reflex.services.ReplayHubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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

//    @RequestMapping(value = "/addFile", method = RequestMethod.POST)
//    public String addFile(@RequestParam(name = "file") MultipartFile file) { //@RequestParam(name = "filename") String fileName, @RequestBody byte[] contents){
//        try {
//            if (!file.isEmpty()) {
//                return replayHubService.addFile(file.getOriginalFilename(), file.getBytes());
//            }
//            return "";
//        } catch (IOException ex){
//            return ex.getLocalizedMessage();
//        }
//    }

    // doing a non-multipart allows for an easy C# WebClient.UploadData()
    @RequestMapping(value = "/addFile", method = RequestMethod.POST)
    public String addFile(@RequestParam(name = "filename") String fileName, @RequestBody byte[] contents){
        return replayHubService.addFile(fileName, contents);
    }
}
