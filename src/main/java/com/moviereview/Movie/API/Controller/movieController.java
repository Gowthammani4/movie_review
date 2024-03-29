package com.moviereview.Movie.API.Controller;

import com.moviereview.Movie.API.Service.movieService;
import com.moviereview.Movie.API.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/movies")
public class movieController {
    @Autowired
    private movieService movieservice;
@GetMapping
@ResponseStatus(HttpStatus.OK)
public List<Movie> allMovies(){
    return movieservice.getAllMovies();
}

@PostMapping("/filter/{releaseDate}")
@ResponseStatus(HttpStatus.OK)
private List<Movie> getMoviesByReleaseDate(@PathVariable String releaseDate){
    return movieservice.filterByReleaseDate(releaseDate);
}

@PostMapping("/filterGenre/{genre}")
@ResponseStatus(HttpStatus.OK)
private List<Movie> getMoviesByGenres(@PathVariable String genre){
    return movieservice.filterByGenres(genre);
}

@PostMapping("/{imdbId}")
    @ResponseStatus(HttpStatus.OK)
    private ResponseEntity<Movie> getMovieById(@PathVariable String imdbId){
    return new ResponseEntity<Movie>(movieservice.getMovieByImdbId(imdbId),HttpStatus.OK);
}
}
