import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormArray, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { JobListingService } from '../../services/joblisting.service';
import { JobListing } from '../../models/joblisting/joblisting';

@Component({
  selector: 'app-update-joblisting',
  templateUrl: './update-joblisting.component.html',
  styleUrls: ['./update-joblisting.component.css']
})
export class UpdateJoblistingComponent implements OnInit {
  updateForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private jobListingService: JobListingService,
    private router: Router,
    private route: ActivatedRoute
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
    }
  }

  get technologies(): FormArray {
    return this.updateForm.get('technologies') as FormArray;
  }

  setTechnologies(technologies: string[]): void {
    const techFGs = technologies.map(tech => this.fb.control(tech, Validators.required));
    const techFormArray = this.fb.array(techFGs);
    this.updateForm.setControl('technologies', techFormArray);
  }

  addTechnology(): void {
    this.technologies.push(this.fb.control('', Validators.required));
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
