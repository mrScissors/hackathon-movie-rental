import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { IUser } from './user';
import { HttpHeaders, HttpClient, HttpParams } from '@angular/common/http';
import { Router } from '@angular/router';
import { IMovie } from './movie';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private initData = [];
  private currentUser: IUser;

  public userDataStore: IUser[];
  public currentUserStore = new BehaviorSubject<IUser>(this.currentUser);

  httpOptions = {
    httpOptions: new HttpHeaders({ 'Content-Type': 'application/json'})
  };

  constructor(public http: HttpClient, private router: Router) { this.loadData(); }

  saveCurrentUser(username: string) {
    // let flag = 0;
    // this.userDataStore.forEach(d => {
    //   if (username === d.username) {
    //     this.currentUser = d;
    //     flag = 1;
    //   }
    // });
    // if (flag === 0) {
    //   console.log('Enter a valid username');
    // } else {
    //   this.router.navigate(['movie-list']);
    // }
    this.router.navigate(['movie-list']);
  }

  loadData() {
    this.http.get<IUser[]>('http://localhost:9090/api/v1/user')
    .subscribe(d => this.userDataStore = [...d]);
  }

  // pushBehaviour(data: IUser[]){
  //   this.userDataStore.next(data);
  // }

  addUser(newUser: IUser) {
    this.http.post<IUser>('http://localhost:9090/api/v1/user', newUser).subscribe((data: IUser) => {
      console.log('data');
    },
    // (error: any) => alert('Server is offline.')
    );
  }

  modUser(modUser: IUser) {
    this.http.put<IUser>('http://localhost:9090/api/v1/user', modUser).subscribe((data: IUser) => {},
    // (error: any) => alert('Server is offline.')
    );
  }

  addMovieToUser(movie: IMovie, modUser: IUser, date: Date) {
    let params = new HttpParams();
    params = params.append('movieId', movie.id);
    params = params.append('username', modUser.username);
    params = params.append('date', date.toString());
    this.http.put('http://localhost:9090/api/v1/user/movie/add', {params}).subscribe((data: IUser) => {});
  }

  extendMovieToUser(movie: IMovie, modUser: IUser, date: Date) {
    let params = new HttpParams();
    params = params.append('movieId', movie.id);
    params = params.append('username', modUser.username);
    params = params.append('date', date.toString());
    this.http.put('http://localhost:9090/api/v1/user/movie/extend', {params}).subscribe((data: IUser) => {});
  }

  cancelMovieInUser(movie: IMovie, modUser: IUser) {
    let params = new HttpParams();
    params = params.append('movieId', movie.id);
    params = params.append('username', modUser.username);
    this.http.put('http://localhost:9090/api/v1/user/movie/cancel', {params}).subscribe((data: IUser) => {});
  }
}
