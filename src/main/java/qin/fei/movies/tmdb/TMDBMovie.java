package qin.fei.movies.tmdb;

import java.util.List;

import org.bson.types.ObjectId;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import qin.fei.movies.model.Review;

//映射TMDB API响应数据结构
@Data
@NoArgsConstructor
@AllArgsConstructor

public class TMDBMovie {

	 private String id;

	 
	// private String tmdbId;
	 
	 @JsonProperty("title")
	 private String title;
	 
	 @JsonProperty("overview")
	 private String overview;
	 
	 @JsonProperty("poster_path") //指定 JSON 字段与属性名称之间的映射关系
	 private String posterPath;

	 @JsonProperty("release_date")
	 private String releaseDate;

	 @JsonProperty("backdrop_path")
	 private String backdropPath;
	 
	// @JsonProperty("genres")
 

}
