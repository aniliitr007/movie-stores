package com.akgcloud.movieinfoservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.akgcloud.movieinfoservice.model.Movie;

@Repository
public interface MovieInfoRepository extends JpaRepository<Movie, Long> {

}
