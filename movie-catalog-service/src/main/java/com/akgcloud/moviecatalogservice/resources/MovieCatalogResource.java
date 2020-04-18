package com.akgcloud.moviecatalogservice.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akgcloud.moviecatalogservice.model.Movie;
import com.akgcloud.moviecatalogservice.model.MovieCatalog;
import com.akgcloud.moviecatalogservice.model.MovieInfo;
import com.akgcloud.moviecatalogservice.service.MovieCatalogService;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

	private static final String KAFKA_TOPIC = "movie_catalog3";

	@Autowired
	private MovieCatalogService movieCatalogService;

	@Autowired
	KafkaTemplate<String, MovieCatalog> kafkaTemplate;

	@RequestMapping("/movies")
	public MovieCatalog getMovieCatalog() {
		// MovieCatalog catalog = createSampleMovieCatalog();
		MovieCatalog catalog = movieCatalogService.getMoviesCatalog();
		/*
		 * create producer using cmd line bin\windows> kafka-console-producer.bat
		 * --broker-list localhost:9092 --topic test
		 */

		kafkaTemplate.send(KAFKA_TOPIC, catalog);
		return catalog;
	}

	public MovieCatalog createSampleMovieCatalog() {
		MovieCatalog catalog = new MovieCatalog();
		List<MovieInfo> movieInfos = new ArrayList<>();
		MovieInfo info = new MovieInfo();
		Movie m = new Movie("1", "Parasite", "oscar winning movie 2020");
		info.setMovie(m);
		info.setRating(5);
		movieInfos.add(info);
		catalog.setMovieInfos(movieInfos);
		return catalog;
	}

}
