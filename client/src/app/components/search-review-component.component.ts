import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-search-review-component',
  templateUrl: './search-review-component.component.html',
  styleUrls: ['./search-review-component.component.css']
})
export class SearchReviewComponentComponent implements OnInit {

  form!: FormGroup
  movieName?: string

  constructor(private fb: FormBuilder, private router: Router) { }

  ngOnInit(): void {
    this.form = this.createForm();
  }

  search() {
    const movieName = this.form?.value['movieName'];
    this.router.navigate(['/moiveResult', movieName]);
  }

  formInvalid(): boolean {
		return this.form.invalid
	}

  private createForm(): FormGroup {
		return this.fb.group({
			movieName : this.fb.control<string>('', [ Validators.required, Validators.minLength(2)]),
		})
	}

}
