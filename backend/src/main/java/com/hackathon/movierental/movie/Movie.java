package com.hackathon.movierental.movie;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class Movie {

    @Id
    private String id;
    private String title;
    private List<String> genre;
    private int releaseYear;
    private List<String> artist;
    private String director;
    private float rating;
    private int availableCopies;
    private int totalCopies;

    public Movie(String id, String title, List<String> genre, int releaseYear, List<String> artist, String director, float rating, int availableCopies, int totalCopies) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.artist = artist;
        this.director = director;
        this.rating = rating;
        this.availableCopies = availableCopies;
        this.totalCopies = totalCopies;
    }

    public Movie(String title, List<String> genre, int releaseYear, List<String> artist, String director, float rating, int availableCopies, int totalCopies) {
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.artist = artist;
        this.director = director;
        this.rating = rating;
        this.availableCopies = availableCopies;
        this.totalCopies = totalCopies;
    }

    public Movie() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getGenre() {
        return genre;
    }

    public void setGenre(List<String> genre) {
        this.genre = genre;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public List<String> getArtist() {
        return artist;
    }

    public void setArtist(List<String> artist) {
        this.artist = artist;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }

    public int getTotalCopies() {
        return totalCopies;
    }

    public void setTotalCopies(int totalCopies) {
        this.totalCopies = totalCopies;
    }
}
