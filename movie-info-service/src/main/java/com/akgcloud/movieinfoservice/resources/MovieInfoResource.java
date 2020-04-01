package com.akgcloud.movieinfoservice.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akgcloud.movieinfoservice.model.Movie;
import com.akgcloud.movieinfoservice.service.MovieInfoService;

@RestController
@RequestMapping("/movies")
public class MovieInfoResource {

	@Autowired
	private MovieInfoService movieInfoService;

	@RequestMapping("/list")
	public List<Movie> getMoviesList() {
		return movieInfoService.getMoviesList();
	}

	@RequestMapping("/{movieId}")
	public Movie getMovieInfo(@PathVariable String movieId) {
		return movieInfoService.getMovieById(Long.valueOf(movieId)).get();
	}

	@PostMapping("/add")
	public Movie addMovie(@RequestBody Movie movie) {
		return movieInfoService.addMovie(movie);
	}

	@PostMapping("/update/{movieId}")
	public Movie editMovie(@PathVariable String movieId, @RequestBody Movie newMovie) {
		Movie movie = movieInfoService.getMovieById(Long.valueOf(movieId)).get();
		movie.setName(newMovie.getName());
		movie.setDescription(newMovie.getDescription());
		return movieInfoService.updateMovie(movie);
	}

	@DeleteMapping("/delete/{movieId}")
	public void deleteMovie(@PathVariable String movieId) {
		movieInfoService.deleteMovie(Long.valueOf(movieId));
	}

}
