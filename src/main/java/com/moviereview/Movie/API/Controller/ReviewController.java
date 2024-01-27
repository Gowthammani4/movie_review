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
    public String createReview(@RequestBody Map<String,String> review){
         reviewService.createReview(review.get("reviewBody"),review.get("imdbId"));
    return "Success";
    }
    @DeleteMapping("/delete/{imdbId}/{userId}")
    public void deleteReview(@PathVariable String userId,@PathVariable String imdbId){
        reviewService.deleteReview(userId,imdbId);
        System.out.println(userId+" deleted successfully");
    }
    @GetMapping("/userReviews")
    @ResponseStatus(HttpStatus.OK)
    private List<Review> currentUserReviews(){return reviewService.findReviewsByCurrentUserId();}
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    private List<Review> allReviews(){
        return reviewService.allReviews();
    }
    @PostMapping("findByImdbId/{imdbId}")
    @ResponseStatus(HttpStatus.OK)
    private List<Review> findByImdbId(@PathVariable String imdbId){
        return reviewService.findByImdbId(imdbId);
    }



    @PostMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    private List<Review> filterReviewsByUserId(@PathVariable String userId){
        return reviewService.filterByUserId(userId);
    }

}
