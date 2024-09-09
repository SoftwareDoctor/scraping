import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormArray, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { JobListingService } from '../../services/joblisting.service';
import { JobListing } from '../../models/joblisting/joblisting';
import { MatDialog } from '@angular/material/dialog';
import {ListatechComponent } from '../../components/listatech/listatech.component';
import { MatIconModule } from '@angular/material/icon';
import { MatChipsModule } from '@angular/material/chips';

@Component({
  selector: 'app-update-joblisting',
  templateUrl: './update-joblisting.component.html',
  styleUrls: ['./update-joblisting.component.css']
})
export class UpdateJoblistingComponent implements OnInit {
  updateForm: FormGroup;
  technologiesList: string[] = [];
   errorMessage: string | null = null;

  constructor(
    private fb: FormBuilder,
    private jobListingService: JobListingService,
    private router: Router,
    private route: ActivatedRoute,
     private dialog: MatDialog
  ) {
    this.updateForm = this.fb.group({
      title: ['', Validators.required],
      jobLink: [''],
      technologies: this.fb.array([])
    });
  }

   ngOnInit(): void {
     const id = this.route.snapshot.paramMap.get('id');
     if (id) {
       this.jobListingService.getJobListingById(id).subscribe(jobListing => {
         this.updateForm.patchValue({
           title: jobListing.title,
           jobLink: jobListing.jobLink
         });
         this.setTechnologies(jobListing.technologies);
       });

       this.jobListingService.getListaTech().subscribe(techs => {
         this.technologiesList = techs;
       });
     }
   }

get technologies(): FormArray {
    return this.updateForm.get('technologies') as FormArray;
  }

openTechnologyDialog(): void {
  const dialogRef = this.dialog.open(ListatechComponent, {
    width: '250px',
    data: { technologies: this.technologiesList }
  });

  dialogRef.afterClosed().subscribe(result => {
    if (result) {
      this.addTechnology(result);
    }
  });
}





   setTechnologies(technologies: string[]): void {
     const techFGs = technologies.map(tech => this.fb.control(tech, Validators.required));
     const techFormArray = this.fb.array(techFGs);
     this.updateForm.setControl('technologies', techFormArray);
   }

addTechnology(tech: string): void {
  const techLower = tech.toLowerCase();
  console.log('Attempting to add technology:', techLower);

  const existing = this.technologies.controls.some(control => control.value.toLowerCase() === techLower);

  if (existing) {
    this.errorMessage = 'Technology already added';
   alert('Technology already added');
    return;
  }

  this.errorMessage = null;
  this.technologies.push(this.fb.control(tech, Validators.required));
  console.log('Technology added:', tech);
}


  removeTechnology(index: number): void {
    this.technologies.removeAt(index);
  }

onSubmit(): void {
   if (this.updateForm.valid) {
     const { jobLink, ...updatedJobListing } = this.updateForm.value;
     updatedJobListing.uuid = this.route.snapshot.paramMap.get('id');

     this.jobListingService.updateJobListing(updatedJobListing).subscribe(
       () => {
         this.router.navigate(['/all']);
       },
       error => {
         console.error('Update failed', error);
       }
     );
   } else {
     console.error('Form is invalid');
   }
 }






}
