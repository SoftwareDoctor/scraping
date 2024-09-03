import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LayoutComponent } from './components/layout/layout.component';
import { HomeComponent } from './pages/home/home.component';
import { CreateJoblinkComponent } from './pages/create-joblink/create-joblink.component';
import { UpdateJoblistingComponent } from './pages/update-joblisting/update-joblisting.component';
import { ConfirmationDialogComponent } from './components/confirmation-dialog/confirmation-dialog.component';
import { JobListingComponent } from './pages/job-listing/job-listing.component';
import { SinglejoblistingComponent } from './pages/singlejoblisting/singlejoblisting.component';


const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },

  {
    path: '',
    component: LayoutComponent,
    children: [
      { path: 'home', component: HomeComponent },
       { path: 'search/:name', component: JobListingComponent },
      { path: 'create', component: CreateJoblinkComponent },
      { path: 'all', component: JobListingComponent },
      { path: 'singlejoblistingupdate/:id', component: UpdateJoblistingComponent },
      { path: 'singlejoblisting/:id', component: SinglejoblistingComponent }
    ]
  },

  { path: 'confirm', component: ConfirmationDialogComponent },
  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
