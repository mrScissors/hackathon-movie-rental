package com.hackathon.movierental;

import com.hackathon.movierental.movie.Movie;
import com.hackathon.movierental.movie.MovieRepository;
import com.hackathon.movierental.movie.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;

@ExtendWith(SpringExtension.class)
class MovieServiceTest {

    @Mock
    MovieRepository movieRepository;

    @InjectMocks
    private MovieService movieService;

    private Movie dummyObj1 ;
    private Movie dummyObj2 ;
    private List<Movie> testList;

    @BeforeEach
    public void setup() throws Exception {
        MockitoAnnotations.initMocks((this));
        dummyObj1= new Movie("5db808775bbfa1ff6c6e8e23", "Shaktiman", List.of("Action", "Drama", "Sci-fi"), 1990, List.of("Mukesh Khanna", "Geeta"), "BBR", 5, 15, 120);
        testList = List.of(dummyObj1);
        System.out.println("i am here");
        Mockito.when(movieRepository.findAll()).thenReturn(testList);
        Mockito.when(movieRepository.save(dummyObj1)).thenReturn(dummyObj1);
//        Mockito.when(movieRepository.deleteAllByTitleContaining(dummyObj1.getTitle());
//        Mockito.verify(movieRepository, Mockito.times(1)).deleteAllByTitleContaining(dummyObj1.getTitle());
    }

//    @Test
//    public void testMovies() {
//        Assert.assertArrayEquals(movieService.getAllMoviesWithParams().toArray(), testList.toArray());
//    }

    @Test
    public void testSaveMovie() {
        Movie expectedObj = dummyObj1;
        Assert.assertEquals(movieService.saveMovie(dummyObj1), expectedObj);
    }

    @Test
    public void updateMovieTest() {
        Movie expectedObj = dummyObj1;
        Assert.assertEquals(movieService.updateMovie(dummyObj1), expectedObj);
    }

//   @Test
//    public void testDeleteMovie() {
//        Movie movies = dummyObj1;
//        Assert.assertEquals(movieService.deleteMovie(dummyObj1.getTitle()), null);
//
//    }

}
