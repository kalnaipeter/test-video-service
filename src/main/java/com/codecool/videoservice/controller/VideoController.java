package com.codecool.videoservice.controller;

import com.codecool.videoservice.entity.Video;
import com.codecool.videoservice.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/video")
@CrossOrigin(origins = "http://localhost:3000")
public class VideoController {

    @Autowired
    private VideoService service;

    @GetMapping("/all")
    public List<Video> getAllWithoutRecommendation(){
        return service.getAllWithoutRecommendation();
    }

    @GetMapping("/{id}")
    public List<Object> getVideoWithRecommendationById(@PathVariable("id")Long id){
        return service.getVideoWithRecommendationById(id);
    }

    @RequestMapping("/update/{id}")
    public void updateVideoById(@PathVariable("id")Long id,
                                @RequestParam("name") String name,
                                @RequestParam("url")String url){
        service.updateVideoById(id,name,url);
    }
}
