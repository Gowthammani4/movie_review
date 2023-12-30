package com.moviereview.Movie.API.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Review")
public class Review {
    @Id
    private ObjectId id;
    private String body;
    private String userId;


    public Review(String body,String user){this.body=body;this.userId=user;}

}
