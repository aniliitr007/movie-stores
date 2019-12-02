package com.akgcloud.moviecatalogservice.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.akgcloud.moviecatalogservice.model.CatalogItem;
import com.akgcloud.moviecatalogservice.model.Movie;
import com.akgcloud.moviecatalogservice.model.Rating;
import com.akgcloud.moviecatalogservice.model.UserRating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
        List<CatalogItem> items = new ArrayList<CatalogItem>();
        UserRating userRating = restTemplate.getForObject("http://movie-rating-service/ratings/users/" + userId,
                UserRating.class);
        List<Rating> ratings = userRating.getUserRating();
        for (Rating rate : ratings) {
            Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rate.getMovieId(), Movie.class);
            items.add(new CatalogItem(movie.getName(), "test desc", rate.getRating()));
        }
        return items;
    }
    
}
