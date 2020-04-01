package com.akgcloud.movieratingservice.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akgcloud.movieratingservice.model.Rating;
import com.akgcloud.movieratingservice.model.UserRating;
import com.akgcloud.movieratingservice.service.MovieRatingService;

@RestController
@RequestMapping("/ratings")
public class RatingResource {

	@Autowired
	private MovieRatingService movieRatingService;

	@RequestMapping("/{movieId}")
	public Rating getRating(@PathVariable String movieId) {
		return movieRatingService.getRatingByMovieId(Integer.valueOf(movieId)).get();
	}

	@RequestMapping("/list")
	public UserRating getRatingsList() {
		UserRating ratings = new UserRating();
		ratings.setUserRating(movieRatingService.getRatingsList());
		return ratings;
	}

	@PostMapping("/add")
	public Rating addRating(@RequestBody Rating rating) {
		return movieRatingService.addRating(rating);
	}

	@PostMapping("/update/{movieId}")
	public Rating editRating(@PathVariable String movieId, @RequestBody Rating newRating) {
		Rating rating = movieRatingService.getRatingByMovieId(Integer.valueOf(movieId)).get();
		rating.setRating(newRating.getRating());
		return movieRatingService.updateRating(rating);
	}

	@DeleteMapping("/delete/{movieId}")
	public void deleteRating(@PathVariable String movieId) {
		movieRatingService.deleteMovieRating(Integer.valueOf(movieId));
	}
}
