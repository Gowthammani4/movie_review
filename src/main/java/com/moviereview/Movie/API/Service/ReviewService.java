package com.moviereview.Movie.API.Service;

import com.moviereview.Movie.API.model.Movie;
import com.moviereview.Movie.API.model.Review;
import com.moviereview.Movie.API.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepo;
    @Autowired
    private MongoTemplate mongoTemplate;

    public Review createReview(String reviewBody,String imdbId,String user){
        Review review=reviewRepo.insert(new Review(reviewBody,user));
        mongoTemplate.update(Movie.class)
                .matching(Criteria.where("imdbId").is(imdbId))
                .apply(new Update().push("reviewIds").value(review)
                ).first();
        return review;
    }
    public void deleteReview(String userId){
        Review review1= reviewRepo.findByUserId(userId);
        System.out.println(review1);
        System.out.println(review1.getId());
        reviewRepo.deleteReviewByUserId(userId);
        Query q=new Query(Criteria.where("Id").is(review1.getId()));
        Update update=new Update().pull("reviewIds",review1.getId());
        mongoTemplate.updateFirst(q,update,Movie.class);
    }
}
