package qin.fei.movies.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection="reviews")
//i dont have reviews collections but the applictaion itself will
//help me create new collection
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {
	@Id
	private String id;
	
	private String body;

	public Review(String body) {
		super();
		this.body = body;
	}
	
	
}
