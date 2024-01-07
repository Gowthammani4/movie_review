package com.moviereview.Movie.API.Controller;
import java.util.*;
import com.moviereview.Movie.API.Service.ReviewService;
import com.moviereview.Movie.API.model.Movie;
import com.moviereview.Movie.API.model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public Review createReview(@RequestBody Map<String,String> payload){
        return reviewService.createReview(payload.get("reviewBody"),payload.get("imdbId"), payload.get("userId"));
    }
    @DeleteMapping("/delete/{imdbId}/{userId}")
    public void deleteReview(@PathVariable String userId,@PathVariable String imdbId){
        reviewService.deleteReview(userId,imdbId);
        System.out.println(userId+" deleted successfully");
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    private List<Review> allMovies(){
        return reviewService.allReviews();
    }

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    private List<Review> filterReviewsByUserId(@PathVariable String userId){
        return reviewService.filterByUserId(userId);
    }

}
