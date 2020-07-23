package com.codecool.videoservice.controller;

import com.codecool.videoservice.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recommendation")
public class RecommendationController {

    @Autowired
    private RecommendationService service;

    @GetMapping("/{id}")
    public List getAllRecommendationByVideoId(@PathVariable("id") Long id){
        return service.getAllRecommendationByVideoId(id);
    }

    @PostMapping("/new/{id}")
    public void addNewRecommendationForVideo(@PathVariable("id")Long id,
                                             @RequestParam("rating")int rating,
                                             @RequestParam("comment")String comment){
        service.addNewRecommendationForVideo(id,rating,comment);
    }
}
