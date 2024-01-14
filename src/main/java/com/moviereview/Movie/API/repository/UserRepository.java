package com.moviereview.Movie.API.repository;

import com.moviereview.Movie.API.model.UserDetails;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<UserDetails, ObjectId> {
UserDetails findByEmailIgnoreCase(String email);
UserDetails findById(String Id);
Boolean existsByEmail(String email);
Boolean existsByUserName(String userName);
void deleteByUserName(String userName);
UserDetails findByUserNameIgnoreCase(String userName);
UserDetails deleteByEmail(String email);
UserDetails findByConfirmationToken(long token);

}
