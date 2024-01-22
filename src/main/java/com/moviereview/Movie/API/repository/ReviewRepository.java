package com.moviereview.Movie.API.repository;

import com.moviereview.Movie.API.model.Review;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ReviewRepository extends MongoRepository<Review, ObjectId> {
    void deleteReviewByUserId(String userId);
    List<Review> findByImdbId(String imdbId);
    Review findByUserIdAndImdbId(String userId,String imdbId);
}
