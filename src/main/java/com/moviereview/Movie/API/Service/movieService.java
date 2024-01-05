package com.moviereview.Movie.API.Service;

import com.moviereview.Movie.API.model.Movie;
import com.moviereview.Movie.API.repository.movieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class movieService {

    @Autowired
    private movieRepository movieRepo;

    public List<Movie> filterByReleaseDate(String releaseDate){
        List<Movie> allMovies=movieRepo.findAll();
        allMovies.removeIf(i -> !i.getImdbId().equals(releaseDate));
        return allMovies;
    }


    public List<Movie> getAllMovies(){
        System.out.println("In service");
        System.out.println(movieRepo.findAll());

        return movieRepo.findAll();
    }
    public Movie getMovieByImdbId(String imdbId){
       return movieRepo.findMovieByImdbId(imdbId);
    }
}
