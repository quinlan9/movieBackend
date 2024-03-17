package qin.fei.movies.repository;

import java.util.List;

import qin.fei.movies.model.Movie;

public interface TMDBRepository {
	
	void seedMoviesFromTMDB(List<Movie> movies);
}
