package com.moviereview.Movie.API.repository;

import com.moviereview.Movie.API.model.Review;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ReviewRepository extends MongoRepository<Review, ObjectId> {
    void deleteReviewByUserId(String userId);
    Review findByUserIdAndImdbId(String userId,String imdbId);
}
