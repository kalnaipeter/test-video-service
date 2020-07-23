package com.codecool.videoservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class RecommendationService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${recommendation.url}")
    private String recommendationUrl;

    public List getAllRecommendationByVideoId(Long id){
        return restTemplate.getForEntity(recommendationUrl + "/" +id,List.class).getBody();
    }

    public void addNewRecommendationForVideo(Long id, int rating, String comment) {
        MultiValueMap<String,String> map =new LinkedMultiValueMap<>();
        map.add("rating",Integer.toString(rating));
        map.add("comment",comment);
        restTemplate.postForLocation(recommendationUrl + "/new/" + id,map);
    }
}
