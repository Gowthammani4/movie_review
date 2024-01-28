package com.moviereview.Movie.API.Service;

import com.moviereview.Movie.API.model.Movie;
import com.moviereview.Movie.API.model.Review;
import com.moviereview.Movie.API.model.currentUser;
import com.moviereview.Movie.API.repository.ReviewRepository;
import com.moviereview.Movie.API.repository.currentUserRepository;
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
    @Autowired
    private currentUserService currService;


    public Review createReview(String reviewBody,String imdbId) {
        currentUser currentuser=currService.getCurrentUser();
        Review review = reviewRepo.insert(new Review(reviewBody,currentuser.getUserId(),imdbId));
        mongoTemplate.update(Movie.class)
                .matching(Criteria.where("imdbId").is(imdbId))
                .apply(new Update().push("reviewIds").value(review)
                ).first();
        return review;
    }
    public List<Review> findByImdbId(String imdbId){
        return reviewRepo.findByImdbId(imdbId);
    }

    public List<Review> findReviewsByCurrentUserId(){
        currentUser currentuser=currService.getCurrentUser();
        return reviewRepo.findByUserId(currentuser.getUserId());

    }
    public void deleteReview(String imdbId){
        currentUser user=currService.getCurrentUser();
        Review review1= reviewRepo.findByUserIdAndImdbId(user.getUserId(),imdbId);
        Movie movies=movieRepo.findMovieByImdbId(imdbId);

        List<Review> reviewIds=movies.getReviewIds();
        List<Review> newIds=new ArrayList<>();
        for (Review reviewId : reviewIds) {
            if (!(reviewId.getUserId().equals(user.getUserId())))
                return;
            if (reviewId.getId().equals(review1.getId())) {
                continue;
            }
            newIds.add(reviewId);
        }
        movies.setReviewIds(newIds);
        movieRepo.save(movies);
        reviewRepo.deleteReviewByUserId(user.getUserId());
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
