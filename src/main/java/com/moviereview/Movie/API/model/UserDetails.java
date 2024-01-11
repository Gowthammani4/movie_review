package com.moviereview.Movie.API.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "UserDetails")
public class UserDetails {
    @Id
    private String id;
    private String userName;
    private String email;
    private String password;
    private Boolean verified;
    public UserDetails(String userName, String email, String password,Boolean verified){this.userName=userName;this.email=email;this.password=password;this.verified=verified;};
}
