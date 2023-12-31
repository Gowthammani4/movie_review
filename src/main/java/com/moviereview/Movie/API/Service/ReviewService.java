package com.moviereview.Movie.API.Service;

import com.moviereview.Movie.API.model.Movie;
import com.moviereview.Movie.API.model.Review;
import com.moviereview.Movie.API.repository.ReviewRepository;
import com.moviereview.Movie.API.repository.movieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
            System.out.println("Inside of reviewId for loop");
            System.out.println(reviewId.getId());
            if (reviewId.getId().equals(review1.getId())) {
                System.out.println("In if condition");
                System.out.println("Deleting "+reviewId.getId()+"2nd"+review1.getId());
                continue;
            }
            newIds.add(reviewId);
        }
        movies.setReviewIds(newIds);
        System.out.println(movies);
        movieRepo.save(movies);
        System.out.println(review1);
        System.out.println(review1.getId());
        reviewRepo.deleteReviewByUserId(userId);
    }
}
