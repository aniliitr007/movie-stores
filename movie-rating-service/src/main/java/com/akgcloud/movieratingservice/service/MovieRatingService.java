package com.akgcloud.movieratingservice.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.akgcloud.movieratingservice.model.Rating;
import com.akgcloud.movieratingservice.repository.MovieRatingRepository;
import com.hazelcast.core.HazelcastInstance;

@Service
@Cacheable(cacheNames = "ratings-cache")
public class MovieRatingService {

	private static final Logger LOGGER = LogManager.getLogger(MovieRatingService.class);

	@Autowired
	private MovieRatingRepository movieRatingRepository;

	@Autowired
	private HazelcastInstance hazelcastInstance;

	public Optional<Rating> getRatingByMovieId(Integer movieId) {
		return movieRatingRepository.findByMovieId(movieId);
	}

	public List<Rating> getRatingsList() {
		LOGGER.info("inside MovieRatingService");
		Map<String, List<Rating>> ratingMap = hazelcastInstance.getMap("ratings-cache");
		List<Rating> ratings = null;
		if (ratingMap.get("list") != null) {
			return ratingMap.get("list");
		} else {
			LOGGER.info("inside db call in MovieRatingService");
			ratings = movieRatingRepository.findAll();
			ratingMap.put("list", ratings);
		}

		return ratings;
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
