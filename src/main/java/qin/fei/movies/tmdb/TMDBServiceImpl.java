package qin.fei.movies.tmdb;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import qin.fei.movies.model.Movie;
import qin.fei.movies.tmdb.TMDBMovie;

//实现从TMDB API获取数据并将其转换为Movie对象
@Service
public class TMDBServiceImpl implements TMDBService{

	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${tmdb.api.key}")
	private String apiKey;
	
	private static final String TMDB_API_URL = "https://api.themoviedb.org/3/movie/now_playing?api_key=%s";
	
	
	/**
	 * 使用 RestTemplate 发送 HTTP 请求到 TMDb API,
	 * 并将响应映射到 TMDbResponse 对象
	 * 然后将 TMDbMovie 对象列表转换为 Movie 对象列表
	 */
	@Override
	public List<Movie> getMoviesFromTMDB() {
		String url=String.format(TMDB_API_URL,apiKey);
		ResponseEntity<TMDBResponse> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<TMDBResponse>() {}
        );
        
        return response.getBody().getResults().stream()
                .map(this::convertToMovieEntity)
                .collect(Collectors.toList());
	}
	
	
	//convert tmdb movie to movie
	private Movie convertToMovieEntity(TMDBMovie tmdbMovie) {
        Movie movie = new Movie();
        movie.setTmdbId(tmdbMovie.getId());
        movie.setTitle(tmdbMovie.getTitle());
        movie.setPosterPath(tmdbMovie.getPosterPath());
        movie.setReleaseDate(tmdbMovie.getReleaseDate());
        // 根据需要映射更多字段
        return movie;
    }
	
	private static class TMDBResponse{
		
		private List<TMDBMovie> results;
		
		public List<TMDBMovie> getResults() {
            return results;
        }
        
        public void setResults(List<TMDBMovie> results) {
            this.results = results;
        }		        
		        
	}
	
}
