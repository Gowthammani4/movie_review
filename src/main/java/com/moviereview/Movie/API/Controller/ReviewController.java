package com.moviereview.Movie.API.Controller;
import java.util.*;
import com.moviereview.Movie.API.Service.ReviewService;
import com.moviereview.Movie.API.model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/review")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody Map<String,String> payload){
        return new ResponseEntity<Review>(reviewService.createReview(payload.get("reviewBody"),payload.get("imdbId"), payload.get("userId")), HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{userId}")
    public void deleteReview(@RequestBody String userId){
        reviewService.deleteReview(userId);
        System.out.println(userId+" deleted successfully");
    }
}
