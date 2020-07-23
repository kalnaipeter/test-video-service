package com.codecool.videoservice.repository;

import com.codecool.videoservice.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface VideoRepository extends JpaRepository<Video,Long> {

    @Transactional
    @Modifying
    @Query("UPDATE Video v set v.name = :name ,v.url = :url WHERE v.id = :id")
    void updateById(@Param("id")Long id, @Param("name")String name, @Param("url")String url);
}
