import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.css']
})
export class LayoutComponent implements OnInit {
  title = 'Software Doctor';
  subtitle = 'Andrea Italiano';
  searchForm!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.searchForm = this.fb.nonNullable.group({
      searchTerm: ['']
    });
  }

  navigateToHome(): void {
    this.router.navigate(['/']);
  }

  public onSearchSubmit(): void {
    const searchTerm = this.searchForm.value.searchTerm ?? '';
    this.router.navigate(['/search'], { queryParams: { searchTerm } });
  }
}


