package com.example.spring12;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.spring12.entity.Post;
import com.example.spring12.repository.PostRepository;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class Spring12ReactApiApplication {
	
	PostRepository repo;
	
	public Spring12ReactApiApplication(PostRepository repo) {
		this.repo = repo;
	}
	
	@PostConstruct
	public void initPost() {
		for(int i=0; i<100; i++) {
			Post p = Post.builder().title("제목" + i).author("작성자" + i).build();
			repo.save(p);
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(Spring12ReactApiApplication.class, args);
	}

}
