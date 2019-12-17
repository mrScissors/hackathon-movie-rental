import { Injectable } from '@angular/core';
import { IMovie } from './movie';
import { BehaviorSubject, Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class MovieRestServiceService {
  private initData = [];
  public selectedMovie: IMovie;
  private dataSource;
  public movieDataStore = new BehaviorSubject<IMovie[]>(this.initData);
  public url = 'http://localhost:9090/api/v1/movies';

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json'})
  };

  constructor(public http: HttpClient, private router: Router ) { this.loadMovies(); }

  saveSelectedMovie(movie: IMovie) {
    this.selectedMovie = movie;
  }

  loadMovies() {
    this.http.get<IMovie[]>('http://localhost:9090/api/v1/movies')
    .subscribe(d => this.movieDataStore.next(d));
  }

  pushBehaviour(data: IMovie[]) {
    this.movieDataStore.next(data);
  }

  movieHttpPush(newObj: IMovie) {
    return this.http.post<IMovie>('http://localhost:9090/api/v1/movies', newObj, this.httpOptions).pipe
    (tap(_ => console.log(`added movie with index=${newObj.id}`)));
  }

  movieHttpPut(modObj: IMovie) {
    return this.http.put<IMovie>('http://localhost:9090/api/v1/movies', modObj, this.httpOptions).pipe(
      tap(_ => console.log(`updated movie index=${modObj.id}`)));
  }

  addToServer(newData: IMovie) {
    this.movieHttpPush(newData).subscribe(
      (data: IMovie) => {
        console.log('data');
        // alert(`Added data ${newData.title}`);
        // this.router.navigate(['inventory']);
      },
      // (error: any) => alert('Error in pushing new data. Server is offline.')
    );
  }

  putToServer(modData: IMovie) {
    this.movieHttpPut(modData).subscribe(
      (data: IMovie) => {
        console.log('data');
      },
      // (error: any) => alert('Error in modifying movie. Server is offline.')
    );
  }

  searchMovies(url) {
    console.log('service url-------------->' + url);
    this.http.get(url).subscribe(data => {this.dataSource = data;
                                          this.movieDataStore.next(this.dataSource);
                                          console.log(this.dataSource);
                                        },
      // error => { alert()}
      );
  }


}
