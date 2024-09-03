import { Router } from '@angular/router';
import { JobListing } from '../../models/joblisting/joblisting';
import { Component, OnInit } from '@angular/core';
import { JobListingService } from '../../services/joblisting.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ConfirmationDialogComponent } from '../../components/confirmation-dialog/confirmation-dialog.component';

@Component({
  selector: 'app-job-listing',
  templateUrl: './job-listing.component.html',
  styleUrls: ['./job-listing.component.css']
})
export class JobListingComponent implements OnInit {
  warningMessage: string | null = null;
  joblistings: JobListing[] = [];

  constructor(
    private jobListingService: JobListingService,
    private router: Router,
    private modalService: NgbModal
  ) { }

  ngOnInit(): void {
    this.getAllJobListings();
  }

  public getAllJobListings(): void {
    this.jobListingService.getAllJobListings().subscribe((jobListings: JobListing[]) => {
      this.joblistings = jobListings;
    });
  }


//   public deleteJobLink(jobListing: JobListing): void {
//     this.jobListingService.deleteJobLink(jobListing.uuid).subscribe(() => {
//       this.joblistings = this.joblistings.filter((jl: JobListing) => jl.uuid !== jobListing.uuid);
//       this.warningMessage = 'Job listing deleted successfully!';
//       setTimeout(() => this.warningMessage = null, 3000);
//     });
//   }

  public deleteJobLink(id: string): void {

    this.jobListingService.deleteJobLink(id).subscribe(
      () => {

        this.joblistings = this.joblistings.filter((jl: JobListing) => jl.uuid !== id);
        this.warningMessage = 'Job listing deleted successfully!';
        setTimeout(() => this.warningMessage = null, 3000);
      }

    );
  }


//   public updateJobListing(jobListing: JobListing): void {
//     this.router.navigate(['/job/', jobListing.uuid]);
//   }

public openConfirmationDialog(jobListing: JobListing): void {
    const modalRef = this.modalService.open(ConfirmationDialogComponent);
    modalRef.componentInstance.jobListing = jobListing;

    modalRef.result.then((result) => {
      if (result === 'yes') {
        this.deleteJobLink(jobListing.uuid);
      }
    }).catch((error) => {
      console.log('Modal closed without confirmation:', error);
    });
  }
}
