package com.moviereview.Movie.API.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "currentUser")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class currentUser {
    @Id
    private String id;
    private String email;
    
    public currentUser(String email){
        this.email=email;
    }
}
