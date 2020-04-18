package com.akgcloud.moviecatalogservice.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.akgcloud.moviecatalogservice.model.Movie;
import com.akgcloud.moviecatalogservice.model.MovieCatalog;
import com.akgcloud.moviecatalogservice.model.MovieInfo;
import com.akgcloud.moviecatalogservice.model.Rating;
import com.akgcloud.moviecatalogservice.model.UserRating;

import reactor.core.publisher.Mono;

@Service
public class MovieCatalogService {

	private static final Logger LOGGER = LogManager.getLogger(MovieCatalogService.class);

	@Autowired
	private RestTemplate restTemplate;

	public MovieCatalog getMoviesCatalog() {
		MovieCatalog catalog = new MovieCatalog();
		UserRating userRating = restTemplate.getForObject("http://movie-rating-service/ratings/list", UserRating.class);
		List<Rating> ratings = userRating.getUserRating();
		List<MovieInfo> movieInfos = new ArrayList<>();
		for (Rating rating : ratings) {
			LOGGER.info("feching for movie id : " + rating.getMovieId());
			Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(),
					Movie.class);
			MovieInfo movieInfo = new MovieInfo();
			movieInfo.setMovie(movie);
			movieInfo.setRating(rating.getRating());
			movieInfos.add(movieInfo);
		}
		catalog.setMovieInfos(movieInfos);

		LOGGER.info("search completed !!!!!!!!!!");
		return catalog;
	}

	/*
	 * TODO : Need to fix response handling through web client
	 * 
	 */
	public List<MovieCatalog> getMoviesCatalogWebClient() {
		List<MovieCatalog> items = new ArrayList<MovieCatalog>();
		UserRating userRating = restTemplate.getForObject("http://movie-rating-service/ratings/list", UserRating.class);
		List<Rating> ratings = userRating.getUserRating();
		Map<Integer, Integer> ratingMap = new HashMap<>();
		List<Mono<Movie>> movies = new ArrayList<>();
		for (Rating rating : ratings) {
			LOGGER.info("feching for movie id : " + rating.getMovieId());
			Mono<Movie> movie = fetchMovieById(rating.getMovieId());
			ratingMap.put(rating.getMovieId(), rating.getRating());
			movies.add(movie);
		}

		List<Movie> result = movies.stream().map(m -> m.block()).collect(Collectors.toList());

		for (Movie res : result) {
			int r = ratingMap.get(Integer.valueOf(res.getId()));
			//items.add(new MovieCatalog(res.getName(), res.getDescription(), r));
		}
		LOGGER.info("search completed !!!!!!!!!!");
		return items;
	}

	public Mono<Movie> fetchMovieById(int movieId) {
		final WebClient movieInfoWebClient = WebClient.builder().build();
		try {
			return movieInfoWebClient.get().uri("http://movie-info-service/movies/" + movieId).retrieve()
					.bodyToMono(Movie.class);
		} catch (Exception e) {
			LOGGER.error("Error in calling movie-info-service", e);
		}
		return null;
	}

}
