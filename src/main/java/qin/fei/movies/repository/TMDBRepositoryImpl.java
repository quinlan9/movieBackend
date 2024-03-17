package qin.fei.movies.repository;

import java.util.List;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import qin.fei.movies.model.Movie;

public class TMDBRepositoryImpl implements TMDBRepository{

	private final MongoTemplate mongoTemplate;

	
	@Autowired
    public TMDBRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;

    }
	

    @Override
    public void seedMoviesFromTMDB(List<Movie> movies) {
        mongoTemplate.remove(new Query(), Movie.class);
        mongoTemplate.insertAll(movies);
    }
	
}