package com.moviereview.Movie.API.repository;

import com.moviereview.Movie.API.model.currentUser;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface currentUserRepository extends MongoRepository<currentUser,String> {


}
