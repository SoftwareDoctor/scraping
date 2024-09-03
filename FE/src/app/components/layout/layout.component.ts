import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup } from '@angular/forms';
import { JobListing } from '../../models/joblisting/joblisting';
import { JobListingService } from '../../services/joblisting.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.css']
})
export class LayoutComponent {
  title = 'Software Doctor';
  subtitle = 'Andrea Italiano';
  joblistings: JobListing[] = [];
  searchForm: FormGroup;
  warningMessage: string | null = null;

  constructor(
    private router: Router,
    private jobListingService: JobListingService,
    private fb: FormBuilder
  ) {
    this.searchForm = this.fb.group({
      search: ['']
    });
  }

  navigateToHome(): void {
    this.router.navigate(['/']);
  }

  public filterResults(title: string): void {
    this.jobListingService.searchJobListings(title).subscribe(
      (results: JobListing[]) => {
        this.joblistings = results;
        this.warningMessage = null;
      },
      (error: HttpErrorResponse) => { // Usa HttpErrorResponse per errori HTTP
        console.error('Error during job search:', error.message);
        this.warningMessage = 'Job search KO!';
      }
    );
  }
}
