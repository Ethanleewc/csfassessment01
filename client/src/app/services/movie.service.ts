import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { lastValueFrom } from 'rxjs';
import { Movies } from '../models/movie';

@Injectable({
  providedIn: 'root'
})
export class MovieService {
  private API_URI: string = "/api/search";

  constructor(private httpClient: HttpClient) { }

  getMovies(movieName: string){
    const params = new HttpParams()
        .set("query", movieName);

    const headers = new HttpHeaders().set('Content-Type', 'application/json; charset=utf-8');

    // return 
    lastValueFrom(this.httpClient
        .get<Movies[]>(this.API_URI, {params: params, headers: headers})).then
        (result=> console.log(result))
        // );
  }

  saveComment(c:Movies) : Promise<any>{
    const headers = new HttpHeaders().set('Content-Type', 'application/json; charset=utf-8');
    const body=JSON.stringify(c);
    console.log("save comment !");
    return lastValueFrom(this.httpClient.post<Movies>(this.API_URI+"/comment" + c.comment_count, body, {headers: headers}));
  }
}
