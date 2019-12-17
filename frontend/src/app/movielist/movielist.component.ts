import { Component, OnInit } from '@angular/core';
import { MovieRestServiceService } from '../movie-rest-service.service';
import { FormBuilder } from '@angular/forms';
import { IMovie } from '../movie';
import { Router } from '@angular/router';

@Component({
  selector: 'app-movielist',
  templateUrl: './movielist.component.html',
  styleUrls: ['./movielist.component.css'],
  styles: []
})
export class MovielistComponent implements OnInit {

  public dataSource;
  searchCategories: string[] = ['Title', 'Genre', 'Cast'];
  default = 'Title';


  searchForm = this.fb.group({
    searchCategory: ['Title'],
    searchQuery: [''],
    // password: ['']
  });


  constructor(private movieService: MovieRestServiceService, private fb: FormBuilder, private router: Router) {
    // this.searchForm.controls['searchCategory'].setValue(this.default, {onlySelf: true});
  }

  ngOnInit() {
    if ( this.movieService.movieDataStore != null) {
      this.movieService.movieDataStore.subscribe(d => this.dataSource = [...d]);
    }
  }

  loadData() {
    
    // this.movieService.movieDataStore.subscribe(d => this.dataSource = [...d]);
    // this.movieService.movieDataStore.next(this.dataSource);
    this.movieService.loadMovies();
  }

  onSubmit() {

    if (this.searchForm.value.searchCategory === 'Title') {
      this.movieService.searchMovies('http://localhost:9090/api/v1/movies?title=' + this.searchForm.value.searchQuery);
    }

    if (this.searchForm.value.searchCategory === 'Genre') {
      this.movieService.searchMovies('http://localhost:9090/api/v1/movies?genre=' + this.searchForm.value.searchQuery);
    }

    if (this.searchForm.value.searchCategory === 'Cast') {
      this.movieService.searchMovies('http://localhost:9090/api/v1/movies?cast=' + this.searchForm.value.searchQuery);
    }
  }

  redirectToDetailedView(movie: IMovie) {
    this.router.navigate([`movie-list/${movie.id}`]);
  }



}
