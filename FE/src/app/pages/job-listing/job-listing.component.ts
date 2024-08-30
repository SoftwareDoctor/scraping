import { Router } from '@angular/router';
import { JobListing } from '../../models/joblisting/joblisting';
import { Component, OnInit } from '@angular/core';
import { JobListingService } from '../../services/joblisting.service';

@Component({
  selector: 'app-job-listing',
  templateUrl: './job-listing.component.html',
  styleUrls: ['./job-listing.component.css']
})
export class JobListingComponent implements OnInit {
  warningMessage: string | null = null;
  joblistings: JobListing[] = [];

  constructor(private jobListingService: JobListingService, private router: Router) { }

  ngOnInit(): void {
    this.getAllJobListings();
  }

  public getAllJobListings(): void {
    this.jobListingService.getAllJobListings().subscribe((jobListings: JobListing[]) => {
      this.joblistings = jobListings;
    });
  }

  public deleteJobLink(jobListing: JobListing): void {
    this.jobListingService.deleteJobLink(jobListing.uuid).subscribe(() => {
      this.joblistings = this.joblistings.filter((jl: JobListing) => jl.uuid !== jobListing.uuid);
      this.warningMessage = 'Job listing deleted successfully!';
      setTimeout(() => this.warningMessage = null, 3000);
    });
  }

  public updateJobListing(jobListing: JobListing): void {
    this.router.navigate(['/jobs/edit', jobListing.uuid]);
  }

  public openConfirmationDialog(jobListing: JobListing): void {
    // Open confirmation dialog and handle response
  }
}
