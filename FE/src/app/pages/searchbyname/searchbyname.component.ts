import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JobListingService } from '../../services/joblisting.service';
import { JobListing } from '../../models/joblisting/joblisting';
import { Router } from '@angular/router';

@Component({
  selector: 'app-searchbyname',
  templateUrl: './searchbyname.component.html',
  styleUrl: './searchbyname.component.css'
})
export class SearchbynameComponent implements OnInit {
joblistings: JobListing[] = [];
  searchTerm: string = '';

  constructor(
    private jobListingService: JobListingService,
    private route: ActivatedRoute,
    private router: Router,
  ) {}

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.searchTerm = params['searchTerm'] || '';
      this.fetchJobListings();
    });
  }

  private fetchJobListings(): void {
    if (this.searchTerm) {
      this.jobListingService.searchJobListings(this.searchTerm).subscribe(
        (joblistings) => {
          this.joblistings = joblistings;
        },
        (error) => {
          console.error('Error fetching job listings:', error);
        }
      );
    }
  }
}
