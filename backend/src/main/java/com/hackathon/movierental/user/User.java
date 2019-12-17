package com.hackathon.movierental.user;

import com.hackathon.movierental.movie.Movie;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Document
public class User {

    @Id
    private String _id;
    private String username;
    private String name;
    private String email;
    private Date dob;
    private Map<String, Date> rentedMovies;

    public User(String _id, String username, String name, String email, Date dob, Map<String, Date> rentedMovies) {
        this._id = _id;
        this.username = username;
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.rentedMovies = rentedMovies;
    }

    public User(String username, String name, String email, Date dob, Map<String, Date> rentedMovies) {
        this.username = username;
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.rentedMovies = rentedMovies;
    }

    public User() {
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Map<String, Date> getRentedMovies() {
        return rentedMovies;
    }

    public void setRentedMovies(Map<String, Date> rentedMovies) {
        this.rentedMovies = rentedMovies;
    }
}
