package com.akgcloud.moviecatalogservice.model;

import java.util.List;

public class MovieCatalog {
	private List<MovieInfo> movieInfos;

	public List<MovieInfo> getMovieInfos() {
		return movieInfos;
	}

	public void setMovieInfos(List<MovieInfo> movieInfos) {
		this.movieInfos = movieInfos;
	}

}
