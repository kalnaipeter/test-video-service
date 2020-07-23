package com.codecool.videoservice.service;

import com.codecool.videoservice.entity.Video;
import com.codecool.videoservice.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VideoService {

    @Autowired
    private VideoRepository repository;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${recommendation.url}")
    private String recommendationUrl;

    public List<Video> getAllWithoutRecommendation(){
        return repository.findAll();
    }

    public List<Object> getVideoWithRecommendationById(Long id) {
        List<Object> list = new ArrayList<>();
        list.add(repository.findById(id));

        List recommendation = restTemplate.getForEntity(recommendationUrl + "/" + id,List.class).getBody();
        list.add(recommendation);
        System.out.println(list);
        return list;
    }

    public void updateVideoById(Long id,String name,String url) {
        repository.updateById(id,name,url);
    }
}
