import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { Movies } from '../models/movie';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { MovieService } from '../services/movie.service';

@Component({
  selector: 'app-movie-reviews-list',
  templateUrl: './movie-reviews-list.component.html',
  styleUrls: ['./movie-reviews-list.component.css']
})
export class MovieReviewsListComponent implements OnInit, OnDestroy {
  movieName = "";
  param$! :  Subscription;
  movies!: Movies[]

  constructor(private activatedRoute: ActivatedRoute, 
    private movieSvc: MovieService, private router: Router){}

  ngOnInit(): void {
    this.param$ = this.activatedRoute.params.subscribe(
      async (params) => {
       this.movieName = params['query'];
       console.log(this.movieName);
       const l = await this.movieSvc.getMovies(this.movieName);
       this.movies = l.details;   
     }
   );
  }

  goHome(){
    this.router.navigate(['/moiveResult']);
  }

  addComment(){
    const queryParams: Params = { movieParam: this.movieName};
    this.router.navigate(['/comment'], {queryParams : queryParams})
  }

  ngOnDestroy(): void {
    this.param$.unsubscribe();
  }



}
