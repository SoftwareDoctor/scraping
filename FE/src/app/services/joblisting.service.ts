import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { apiUrl } from '../../environments/environment';
import { JobLink } from '../models/joblink/joblink';
import { JobListing } from '../models/joblisting/joblisting';
import { map } from 'rxjs/operators';


@Injectable({
  providedIn: 'root'
})
export class JobListingService {

  private apiUrl = `${apiUrl}/api/v1/spy`;

  constructor(private http: HttpClient) { }

 public createJobLink(joblink: JobLink): Observable<void> {
    return this.http.post<void>(`${this.apiUrl}/new`, joblink);
  }

public searchJobListings(searchTerm: string): Observable<JobListing[]> {
  return this.http.get<JobListing[]>(`${this.apiUrl}/search/?title=${searchTerm}`);
}


public updateJobListing(jobListing: JobListing): Observable<void> {
  return this.http.patch<void>(`${this.apiUrl}/job/${jobListing.uuid}`, jobListing);
  }


  public deleteJobLink(jobId: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${jobId}`);
  }

  public getAllJobListings(): Observable<JobListing[]> {
    return this.http.get<JobListing[]>(`${this.apiUrl}/`);
  }

  public getJobListingById(id: string): Observable<JobListing> {
    return this.http.get<JobListing>(`${this.apiUrl}/${id}`);
  }

public getListaTech(): Observable<any[]>{
  return this.http.get<any[]>(`http://localhost:8080/api/v1/tech/all`)
    .pipe(
      map(technologies => technologies.sort())
    );
}





}

