package com.akgcloud.movieinfoservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akgcloud.movieinfoservice.model.Movie;
import com.akgcloud.movieinfoservice.repository.MovieInfoRepository;

@Service
public class MovieInfoService {

	@Autowired
	private MovieInfoRepository movieInfoRepository;

	public Optional<Movie> getMovieById(Long id) {
		return movieInfoRepository.findById(id);
	}

	public List<Movie> getMoviesList() {
		return movieInfoRepository.findAll();
	}

	public Movie addMovie(Movie movie) {
		return movieInfoRepository.save(movie);
	}

	public Movie updateMovie(Movie movie) {
		return movieInfoRepository.saveAndFlush(movie);
	}

	public void deleteMovie(Long movieId) {
		movieInfoRepository.deleteById(movieId);
	}

}
