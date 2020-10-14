package com.cos.crawling.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.crawling.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Integer>{

}
