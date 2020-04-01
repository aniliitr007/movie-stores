package com.akgcloud.movieratingservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akgcloud.movieratingservice.model.Rating;
import com.akgcloud.movieratingservice.repository.MovieRatingRepository;

@Service
public class MovieRatingService {

	@Autowired
	private MovieRatingRepository movieRatingRepository;

	public Optional<Rating> getRatingByMovieId(Integer movieId) {
		return movieRatingRepository.findByMovieId(movieId);
	}

	public List<Rating> getRatingsList() {
		return movieRatingRepository.findAll();
	}

	public Rating addRating(Rating rating) {
		return movieRatingRepository.save(rating);
	}

	public Rating updateRating(Rating rating) {
		return movieRatingRepository.save(rating);
	}

	public void deleteMovieRating(Integer movieId) {
		movieRatingRepository.deleteByMovieId(movieId);
	}

}
