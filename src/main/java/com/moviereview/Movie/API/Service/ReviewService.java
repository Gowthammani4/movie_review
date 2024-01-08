package com.moviereview.Movie.API.Service;

import com.moviereview.Movie.API.model.Movie;
import com.moviereview.Movie.API.model.Review;
import com.moviereview.Movie.API.repository.ReviewRepository;
import com.moviereview.Movie.API.repository.movieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepo;
    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private movieRepository movieRepo;

    public Review createReview(String reviewBody,String imdbId,String user) {
        Review review = reviewRepo.insert(new Review(reviewBody,user,imdbId));
        mongoTemplate.update(Movie.class)
                .matching(Criteria.where("imdbId").is(imdbId))
                .apply(new Update().push("reviewIds").value(review)
                ).first();
        return review;
    }
    public void deleteReview(String userId,String imdbId){
        Review review1= reviewRepo.findByUserIdAndImdbId(userId,imdbId);
        Movie movies=movieRepo.findMovieByImdbId(imdbId);

        List<Review> reviewIds=movies.getReviewIds();
        List<Review> newIds=new ArrayList<>();
        for (Review reviewId : reviewIds) {
            if (reviewId.getId().equals(review1.getId())) {
                continue;
            }
            newIds.add(reviewId);
        }
        movies.setReviewIds(newIds);
        movieRepo.save(movies);
        reviewRepo.deleteReviewByUserId(userId);
    }
    public List<Review> allReviews(){
        return reviewRepo.findAll();
    }
    

    public List<Review> filterByUserId(String userId){
        List<Review> allReviews=reviewRepo.findAll();
        System.out.println("")
        allReviews.removeIf(i -> !i.getUserId().equals(userId));
        return allReviews;
    }
}
