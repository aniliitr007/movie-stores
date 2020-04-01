package com.akgcloud.movieratingservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.akgcloud.movieratingservice.model.Rating;

@Repository
public interface MovieRatingRepository extends JpaRepository<Rating, Integer> {

	Optional<Rating> findByMovieId(Integer movieId);

	void deleteByMovieId(Integer movieId);

}
