package com.moviereview.Movie.API.repository;

import com.moviereview.Movie.API.model.Movie;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository

public interface movieRepository extends MongoRepository<Movie,ObjectId> {
    Movie findMovieByImdbId(String imdbId);
    Movie findMovieByReleaseDate(String releaseDate);

}
