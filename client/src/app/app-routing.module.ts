import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SearchReviewComponentComponent } from './components/search-review-component.component';
import { MovieReviewsListComponent } from './components/movie-reviews-list.component';
import { PostCommentComponent } from './components/post-comment.component';

const routes: Routes = [
  {path:'', component:SearchReviewComponentComponent },
  {path: 'moiveResult/:movieName', component: MovieReviewsListComponent},
  {path: 'comment', component: PostCommentComponent},
  {path: '**', redirectTo: '', pathMatch: 'full'} 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
