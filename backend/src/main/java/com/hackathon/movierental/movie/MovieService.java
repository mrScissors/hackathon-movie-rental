package com.hackathon.movierental.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    int page_size = 10;

    public List<Movie> getAllMoviesWithParams(Map<String, String> allParams){
        if(allParams.containsKey("genre")){
            if(allParams.containsKey("pageSize")){
                page_size = Integer.parseInt(allParams.get("pageSize"));
            }
            if(allParams.containsKey("pageNo")){
                Pageable pageable = PageRequest.of(Integer.parseInt(allParams.get("pageNo")),page_size);
                switch (allParams.get("sort")) {

                    case "rating":
                        return movieRepository.findByGenreContainsOrderByRatingDesc(allParams.get("genre"), pageable);
                    case "title":
                        return movieRepository.findByGenreContainsOrderByTitleAsc(allParams.get("genre"), pageable);
                    case "releaseYear":
                        return movieRepository.findByGenreContainsOrderByReleaseYearDesc(allParams.get("genre"), pageable);
                }
            }
            return movieRepository.findByGenreContains(allParams.get("genre"));
        }
        else if(allParams.containsKey("title")){
            return movieRepository.findByTitleContains(allParams.get("title"));
        }
        else if(allParams.containsKey("cast")){
            return movieRepository.findByArtistContaining(allParams.get("cast"));
        }
        else if(allParams.containsKey("director")){
            return movieRepository.findByDirectorContaining(allParams.get("director"));
        }
        else if(allParams.containsKey("sort")){
            if(allParams.containsKey("pageSize")){
                page_size = Integer.parseInt(allParams.get("pageSize"));
            }

            if(allParams.containsKey("pageNo")){
                Pageable pageable = PageRequest.of(Integer.parseInt(allParams.get("pageNo")), page_size);
                if(allParams.get("sort").equals("rating")){
                    return movieRepository.findAllByOrderByRatingDesc(pageable);
                }
                else{
                    return movieRepository.findAllByOrderByTitleAsc(pageable);
                }
            }

            else{

                if(allParams.get("sort").equals("title")) {
                    return movieRepository.findAllByOrderByTitleAsc();
                }
                else if(allParams.get("sort").equals("rating")) {
                    return movieRepository.findAllByOrderByRatingDesc();
                }
                else if(allParams.get("sort").equals("releaseYear")) {
                    return movieRepository.findAllByOrderByReleaseYearDesc();
                }
            }
        }
        return movieRepository.findAll();
    }

    public Movie saveMovie(Movie movieToBeSaved) {
        if(movieToBeSaved.getTitle() != null) {
            return movieRepository.save(movieToBeSaved);
        }
        return movieToBeSaved;
    }

    public Movie updateMovie(Movie movie){
        return movieRepository.save(movie);
    }

    public List<Movie> deleteMovie(String title){
        List<Movie> movies = movieRepository.findByTitleContains(title);
        movieRepository.deleteAllByTitleContaining(title);
        return movies;
    }

    public void deleteMovieByObject(Movie movie){
        movieRepository.delete(movie);
    }

    public boolean updateMovieSubscribed(String movieId, int increment){
        Optional<Movie> movieOptional = movieRepository.findById(movieId);
        Movie movie = movieOptional.get();
        if(increment < 0 && movie.getAvailableCopies() > 0) {
            movie.setAvailableCopies(movie.getAvailableCopies() + increment);
            return true;
        }
        else if(increment > 0 && movie.getAvailableCopies() >= 0){
            movie.setAvailableCopies(movie.getAvailableCopies() + increment);
            return true;
        }

        return false;
    }

}
