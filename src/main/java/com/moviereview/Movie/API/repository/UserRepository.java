package com.moviereview.Movie.API.repository;

import com.moviereview.Movie.API.model.UserDetails;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<UserDetails, ObjectId> {
UserDetails findByEmailIgnoreCase(String email);
Boolean existsByEmail(String email);
}
