import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { JobListingService } from '../../services/joblisting.service';
import { JobLink } from '../../models/joblink/joblink';
import { Router } from '@angular/router'; // Importa Router

@Component({
  selector: 'app-create-joblink',
  templateUrl: './create-joblink.component.html',
  styleUrls: ['./create-joblink.component.css']
})
export class CreateJoblinkComponent {

  jobLinkForm: FormGroup;
  errorMessage: string | null = null;

  constructor(private fb: FormBuilder, private jobListingService: JobListingService, private router: Router) {
    this.jobLinkForm = this.fb.group({
      stringaLink: ['', Validators.required],
    });
  }

  onSubmit() {
    if (this.jobLinkForm.valid) {
      const newJobLink: JobLink = {
        uuid: '', // L'UUID è generato dal backend
        stringaLink: this.jobLinkForm.value.stringaLink
      };

           this.jobListingService.createJobLink(newJobLink).subscribe({
             next: () => {
               // Gestisci la risposta positiva
               alert('Job link creato con successo!');
               this.router.navigate(['/home']); // Reindirizza alla homepage
             },
             error: (err) => {
               // Gestisci l'errore
               console.error('Errore durante la creazione del job link:', err);
               this.errorMessage = 'Errore durante la creazione del job link. Controlla la console per dettagli.';

               // Specifica il messaggio se il link esiste già
               if (err.error && err.error.includes('JobLink already exists')) {
                 this.errorMessage = 'Il link esiste già nel database.';
               } else {
                 this.errorMessage = 'Errore durante la creazione del job link.';
               }
             }
           });
         }
       }
     }
