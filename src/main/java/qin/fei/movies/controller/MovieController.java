package qin.fei.movies.controller;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import qin.fei.movies.model.Movie;
import qin.fei.movies.repository.MovieRepository;
import qin.fei.movies.service.MovieService;
import qin.fei.movies.tmdb.TMDBService;

@RestController
@RequestMapping("/api/movies")
//set the base path for request mapping within this controller. 
//every request mapping path in this controller will be prefixed with 'api/v1/movies'
public class MovieController {
	
	@Autowired
	private MovieService movieService;
	

	private final MovieRepository movieRepository;
    private final TMDBService tmdbService;

    @Autowired
    public MovieController(MovieRepository movieRepository, TMDBService tmdbService) {
        this.movieRepository = movieRepository;
        this.tmdbService = tmdbService;
    }
	
	@GetMapping
	//It's a composed annotation that acts as a shortcut for
	//@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Movie>> getAllMovies() {
		return new ResponseEntity<List<Movie>>(movieService.allMovies(),HttpStatus.OK);
		//httpstatus.ok means 200
	}
	
	@GetMapping("/{tmdbId}")
	//search a movie by its id
	public ResponseEntity<Optional<Movie>> getSingleMovie(@PathVariable String imdbId){
		return new ResponseEntity<Optional<Movie>>(movieService.singleMovie(imdbId),HttpStatus.OK);
	}
	
	
	@GetMapping("/search")
	public ResponseEntity<List<Movie>> searchMovies(@RequestParam String query){
		List<Movie> movies=movieService.searchMovies(query);
		return new ResponseEntity<>(movies,HttpStatus.OK);
	}
  
	@GetMapping("/seed")
    public ResponseEntity<String> seedMoviesFromTMDb() {
        movieRepository.seedMoviesFromTMDB(tmdbService.getMoviesFromTMDB());
        return ResponseEntity.ok("Movies seeded from TMDb API");
	
	}
}
