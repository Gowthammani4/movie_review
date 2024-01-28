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
    private String userId;


    public currentUser(String email, String userName){
        this.email=email;
        this.userId=userName;
    }
}
