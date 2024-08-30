import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JobListingService } from '../../services/joblisting.service';
import { HttpParams } from '@angular/common/http';
import { JobLink } from '../../models/joblink/joblink';

@Component({
  selector: 'app-search-by-name',
  templateUrl: './search-by-name.component.html',
  styleUrls: ['./search-by-name.component.css']
})
export class SearchByNameComponent implements OnInit {
  jobLinks: JobLink[] = [];

  constructor(
    private route: ActivatedRoute,
    private jobListingService: JobListingService
  ) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const name = params.get('name');
      if (name) {
        const searchParams = new HttpParams().set('name', name);
        this.jobListingService.searchJobListings(searchParams)
          .subscribe(jobLinks => this.jobLinks = jobLinks);
      }
    });
  }
}
