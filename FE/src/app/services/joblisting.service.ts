import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { apiUrl } from '../../environments/environment';
import { JobLink } from '../models/joblink/joblink';
import { JobListing } from '../models/joblisting/joblisting';

@Injectable({
  providedIn: 'root'
})
export class JobListingService {

  private apiUrl = `${apiUrl}/api/v1/spy`;

  constructor(private http: HttpClient) { }

 public createJobLink(joblink: JobLink): Observable<void> {
    return this.http.post<void>(`${this.apiUrl}/new`, joblink);
  }

 public searchJobListings(searchParams: HttpParams): Observable<JobLink[]> {
    return this.http.get<JobLink[]>(`${this.apiUrl}/search/`, { params: searchParams });
  }

 public updateJobLink(jobId: string, joblink: JobLink): Observable<void> {
    return this.http.put<void>(`${this.apiUrl}/job/${jobId}`, joblink);
  }

  public deleteJobLink(jobId: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/delete/${jobId}`);
  }

  public getAllJobListings(): Observable<JobListing[]> {
    return this.http.get<JobListing[]>(`${this.apiUrl}/`);
  }
}
