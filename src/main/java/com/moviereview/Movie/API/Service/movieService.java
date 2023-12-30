package com.moviereview.Movie.API.Service;

import com.moviereview.Movie.API.model.Movie;
import com.moviereview.Movie.API.repository.movieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class movieService {

    @Autowired
    private movieRepository movieRepo;


    public List<Movie> getAllMovies(){
        System.out.println("In service");
        System.out.println(movieRepo.findAll());
        return movieRepo.findAll();
    }
    public Optional<Movie> getMovieByImdbId(String imdbId){
       return movieRepo.findMovieByImdbId(imdbId);
    }
}
