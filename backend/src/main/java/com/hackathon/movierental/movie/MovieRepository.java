package com.hackathon.movierental.movie;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends MongoRepository<Movie, String> {

    public List<Movie> findByTitleContains(String title);
    public void deleteAllByTitleContaining(String title);
    public List<Movie> findByGenreContains(String genre);
    public List<Movie> findByReleaseYear(int year);
    public List<Movie> findByArtistContaining(String artistName);
    public List<Movie> findByDirectorContaining(String director);
    public List<Movie> findByGenreContainsOrderByReleaseYearDesc(String genre, Pageable pageable);
    public List<Movie> findByGenreContainsOrderByTitleAsc(String genre, Pageable pageable);
    public List<Movie> findByGenreContainsOrderByRatingDesc(String genre, Pageable pageable);
    public List<Movie> findAllByOrderByRatingDesc(Pageable pageable);
    public List<Movie> findAllByOrderByTitleAsc(Pageable pageable);
    public List<Movie> findAllByOrderByRatingDesc();
    public List<Movie> findAllByOrderByTitleAsc();
    public List<Movie> findAllByOrderByReleaseYearDesc();
}
