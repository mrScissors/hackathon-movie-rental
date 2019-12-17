import { Component, OnInit } from '@angular/core';
import { IMovie } from '../movie';
import { UserService } from '../user.service';
import { MovieRestServiceService } from '../movie-rest-service.service';
import { Router, ParamMap, ActivatedRoute } from '@angular/router';
import { IUser } from '../user';
import { FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-movie-detail',
  templateUrl: './movie-detail.component.html',
  styleUrls: ['./movie-detail.component.css']
})
export class MovieDetailComponent implements OnInit {

  constructor(private fb: FormBuilder,
              private userServ: UserService,
              private movieServ: MovieRestServiceService,
              private router: Router,
              private route: ActivatedRoute) { }

  public movieView: IMovie;
  public user: IUser;
  public rented: boolean;
  public tillDate: Date;
  public clickedDateReqField = false;
  public Id: string;

  dateForm = this.fb.group({
      date: ['']
  });

  ngOnInit() {
    this.route.paramMap.subscribe((params: ParamMap) => {
      this.Id = params.get('id');
    });
    if (this.movieServ != null) {
      this.movieServ.movieDataStore.subscribe(data => this.movieView = data.find(d => d.id === this.Id));
    }
    if (this.userServ.currentUserStore != null) {
      this.userServ.currentUserStore.subscribe(d => this.user = d);
    }
    if (this.movieView != null && this.user != null) {
      this.rented = this.user.rentedMovies.has(this.movieView.id);
      this.tillDate = this.user.rentedMovies.get(this.movieView.id);
    }
  }

  extendBooking() {
    this.userServ.extendMovieToUser(this.movieView, this.user, this.dateForm.value.date);
  }

  cancelBooking() {
    this.userServ.cancelMovieInUser(this.movieView, this.user );
  }

  addBooking() {
    this.userServ.addMovieToUser(this.movieView, this.user, this.dateForm.value.date);
  }

  onSubmit() {
    if (this.rented) {
      this.extendBooking();
    } else {
      this.addBooking();
    }
  }

}
