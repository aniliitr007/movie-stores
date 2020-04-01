package com.akgcloud.moviecatalogservice.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.akgcloud.moviecatalogservice.model.CatalogItem;
import com.akgcloud.moviecatalogservice.model.Movie;
import com.akgcloud.moviecatalogservice.model.Rating;
import com.akgcloud.moviecatalogservice.model.UserRating;
import com.akgcloud.moviecatalogservice.service.MovieCatalogService;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

	@Autowired
	private MovieCatalogService movieCatalogService;
	
	@RequestMapping("/movies")
	public List<CatalogItem> getMovieCatalog() {
		return movieCatalogService.getMoviesCatalog();
	}

}
