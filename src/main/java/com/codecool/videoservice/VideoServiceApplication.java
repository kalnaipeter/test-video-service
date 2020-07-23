package com.codecool.videoservice;

import com.codecool.videoservice.entity.Video;
import com.codecool.videoservice.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableEurekaClient
public class VideoServiceApplication {

	@Autowired
	private VideoRepository videoRepository;

	public static void main(String[] args) {
		SpringApplication.run(VideoServiceApplication.class, args);
	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

	@Bean
	public Docket api(){
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build();
	}

	@Bean
	@Profile("production")
	public CommandLineRunner init(){
		return args -> {
			Video video = Video.builder()
					.name("Crawl")
					.url("https://www.youtube.com/watch?v=H6MLJG0RdDE")
					.build();

			Video video2 = Video.builder()
					.name("47 Meters Down: Uncaged")
					.url("https://www.youtube.com/watch?v=AvXjx8SZbv8")
					.build();

			Video video3 = Video.builder()
					.name("Highkeeper Ra 40 dmg OTK Combo")
					.url("https://www.youtube.com/watch?v=Tom4XAUoeZ0")
					.build();

			videoRepository.save(video);
			videoRepository.save(video2);
			videoRepository.save(video3);
		};
	}
}
