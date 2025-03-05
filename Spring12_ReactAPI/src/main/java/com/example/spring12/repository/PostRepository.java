package com.example.spring12.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring12.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long>{

}
