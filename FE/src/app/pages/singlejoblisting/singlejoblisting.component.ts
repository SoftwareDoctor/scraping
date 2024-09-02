import { ActivatedRoute } from '@angular/router';
import { JobListing } from '../../models/joblisting/joblisting';
import { Component, OnInit } from '@angular/core';
import { JobListingService } from '../../services/joblisting.service';

@Component({
  selector: 'app-singlejoblisting',
  templateUrl: './singlejoblisting.component.html',
  styleUrl: './singlejoblisting.component.css'
})
export class SinglejoblistingComponent implements OnInit{

  jobListing: JobListing | null = null;

   constructor(
      private jobListingService: JobListingService,
      private route: ActivatedRoute
    ) {}

 ngOnInit(): void {
    const jobId = this.route.snapshot.paramMap.get('id');
    if (jobId) {
      this.jobListingService.getJobListingById(jobId).subscribe({
        next: (jobListing: JobListing) => {
          this.jobListing = jobListing;
        },
//         error: (err) => {
//           console.error('Error fetching job listing', err);
//         }
      });
    }
  }
}
/*
Nel contesto del SinglejoblistingComponent, usiamo ActivatedRoute principalmente per ottenere il jobId dalla URL attuale. Questo jobId viene poi passato al servizio JobListingService per recuperare i dettagli del job listing specifico dal backend.
Il metodo ngOnInit è uno dei lifecycle hooks di Angular, utilizzato per eseguire il codice al momento dell'inizializzazione di un componente.
this.route.snapshot: Accede a un'istantanea statica dello stato attuale della rotta. ActivatedRoute ha un concetto di "snapshot", che è uno stato immutabile della rotta attiva in quel preciso momento.
this.jobListing = jobListing;
Una volta ricevuto il job listing dal backend, lo assegniamo alla proprietà this.jobListing del componente. Questo permette al template del componente di accedere ai dettagli del job e di visualizzarli.
*/
