package com.hackathon.movierental.user;

import com.hackathon.movierental.movie.Movie;
import com.hackathon.movierental.movie.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    MovieService movieService;

    public User getUserByUserName(String username){
        return userRepository.findByUsername(username);
    }

    public User saveUser(User user){
        if(user!=null){
            return userRepository.save(user);
        }
        return null;
    }

    public void deleteUser(String username){
        User userToBeDeleted = getUserByUserName(username);
        if(userToBeDeleted != null){
            userRepository.delete(userToBeDeleted);
        }
    }

    public User addUserMovies(String movieId, String username, Date dueDate){
        User user = getUserByUserName(username);
        if(user!=null){
            if(movieService.updateMovieSubscribed(movieId, -1)) {
                Map<String, Date> toBeModified = user.getRentedMovies();
                toBeModified.put(movieId, dueDate);
                user.setRentedMovies(toBeModified);
                userRepository.save(user);
            }
            else{
                System.out.println("Enter a valid MovieId");
            }
        }
        return user;
    }

    public User extendMovie(String movieId, String username, Date newDueDate){
        User user = getUserByUserName(username);
        if(user!=null){
            Map<String, Date> toBeModified = user.getRentedMovies();
            toBeModified.put(movieId, newDueDate);
            user.setRentedMovies(toBeModified);
            userRepository.save(user);
        }
        return user;
    }

    public User deleteMovie(String movieId, String username){
        User user = getUserByUserName(username);
        if(user!=null){
            Map<String, Date> toBeModified = user.getRentedMovies();
            toBeModified.remove(movieId);
            user.setRentedMovies(toBeModified);
            userRepository.save(user);
        }
        return user;
    }

}
