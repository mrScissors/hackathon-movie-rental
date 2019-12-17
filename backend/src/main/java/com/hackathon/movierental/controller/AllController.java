package com.hackathon.movierental.controller;

import com.hackathon.movierental.movie.Movie;
import com.hackathon.movierental.movie.MovieService;
import com.hackathon.movierental.user.User;
import com.hackathon.movierental.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1")
public class AllController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private UserService userService;

    @GetMapping("/movies")
    public List<Movie> getAllMovies(@RequestParam Map<String, String> allParams){
        return movieService.getAllMoviesWithParams(allParams);
    }

    @PostMapping("/movies")
    public Movie addMovie(@RequestBody Movie newMovie){
        return movieService.saveMovie(newMovie);
    }

    @PutMapping("/movies")
    public Movie updateMovie(@RequestBody Movie movieToBeUpdated){
        return movieService.updateMovie(movieToBeUpdated);
    }

    @DeleteMapping("/movies")
    public void deleteMovie(@RequestParam String title){
        movieService.deleteMovie(title);
    }

    @GetMapping("/user")
    public User getAllUsers(@RequestParam Map<String, String> allParams){
        return userService.getUserByUserName(allParams.get("user"));
    }

    @PostMapping("/user")
    public User addUser(@RequestBody User user){
        return userService.saveUser(user);
    }

    @PutMapping("/user/movie/add")
    public User addNewMovie(@RequestParam Map<String, String> allParams) throws Exception{
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(allParams.get("dueDate"));
        return userService.addUserMovies(allParams.get("movieId"), allParams.get("username"),date);
    }

    @PutMapping("/user/movie/extend")
    public User updateMovie(@RequestParam Map<String, String> allParams) throws Exception{
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(allParams.get("dueDate"));
        return userService.extendMovie(allParams.get("movieId"), allParams.get("username"), date);
    }

    @PutMapping("/user/movie/cancel")
    public User deleteMovie(@RequestParam Map<String, String> allParams) throws Exception{
        return userService.deleteMovie(allParams.get("movieId"), allParams.get("username"));
    }

    @DeleteMapping("/user")
    public void deleteUser(@RequestParam String username){
        userService.deleteUser(username);
    }
}
