import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { MovieService } from '../services/movie.service';
import { Movies } from '../models/movie';

@Component({
  selector: 'app-post-comment',
  templateUrl: './post-comment.component.html',
  styleUrls: ['./post-comment.component.css']
})
export class PostCommentComponent implements OnInit, OnDestroy {
  form!: FormGroup;
  queryParams$! :  Subscription;
  movieName!: String

  constructor(private activatedRoute: ActivatedRoute,  private fb: FormBuilder,
    private movieSvc: MovieService, private router: Router){

  }

  ngOnInit(): void {
    this.form = this.createForm();
  }
  ngOnDestroy(): void {
    throw new Error('Method not implemented.');
  }

  saveComment(){
    const commentFormVal = this.form?.value['comment'];
    const c = {} as Movies;
    c.comment_count = commentFormVal;
    c.display_title = this.movieName;
    this.movieSvc.saveComment(c);
    this.router.navigate(['/moiveResult', this.movieName]);
  }

  goBack(){
    this.router.navigate(['/']);
  }

  private createForm(): FormGroup{
    return this.fb.group({
			name : this.fb.control<string>('', [ Validators.required, Validators.minLength(3)]),
      rating: this.fb.control<number>(1, [ Validators.required]),
      comment : this.fb.control<string>('', [ Validators.required])
		})
  }

}
