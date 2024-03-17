package qin.fei.movies.tmdb;

import java.util.List;

import qin.fei.movies.model.Movie;

public interface TMDBService {

	List<Movie> getMoviesFromTMDB();
}
